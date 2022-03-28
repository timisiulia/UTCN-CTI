library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity test_env is
    Port (  an : out STD_LOGIC_VECTOR (3 downto 0);
          cat : out STD_LOGIC_VECTOR (6 downto 0);
           clk : in STD_LOGIC;          
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0)
          );
end test_env;

Architecture testare_id_uc of test_env is
component mpg 
 Port (
        clk: in STD_LOGIC;
        btn: in STD_LOGIC;
        enable: out STD_LOGIC
       );
end component;
component SSD 
 Port ( 
     an: out STD_LOGIC_VECTOR(3 downto 0);
     cat: out STD_LOGIC_VECTOR(6 downto 0);
     clk: in STD_LOGIC;
     digit0: in STD_LOGIC_VECTOR(3 downto 0);
     digit1:in STD_LOGIC_VECTOR(3 downto 0);
     digit2:in STD_LOGIC_VECTOR(3 downto 0);
     digit3:in STD_LOGIC_VECTOR(3 downto 0)
 );
end component;
component register_file_lab6
port(

           clk : in STD_LOGIC;
           ra1 : in STD_LOGIC_VECTOR (2 downto 0);
           ra2 : in STD_LOGIC_VECTOR (2 downto 0);
           wa : in STD_LOGIC_VECTOR (2 downto 0);
           wd : in STD_LOGIC_VECTOR (15 downto 0);
           en : in STD_LOGIC;
           rd1 : out STD_LOGIC_VECTOR (15 downto 0);
           rd2 : out STD_LOGIC_VECTOR (15 downto 0);
           regwrite: in STD_LOGIC
);

end component;
component fetch
port ( jump: in STD_LOGIC;
       jump_address: in STD_LOGIC_VECTOR(15 downto 0);
       pcsrc: in STD_LOGIC;
       branchaddress: in STD_LOGIC_VECTOR(15 downto 0);
       en: in STD_LOGIC;
       rst: in STD_LOGIC;
       clk: in STD_LOGIC;
       pc_plus_1: out STD_LOGIC_VECTOR(15 downto 0);
       instruction: out STD_LOGIC_VECTOR(15 downto 0)
);
end component;

component bram is
Port (
           clk : in STD_LOGIC;
           we : in STD_LOGIC;
           en : in STD_LOGIC;
           addr : in STD_LOGIC_VECTOR (3 downto 0);
           di : in STD_LOGIC_VECTOR (15 downto 0);
           do : out STD_LOGIC_VECTOR (15 downto 0));
end component;

component id is
port(
regwrite: in STD_LOGIC;
instr: in STD_LOGIC_VECTOR(15 downto 0);
regdst: in STD_LOGIC;
clk: in STD_LOGIC;
en: in STD_LOGIC;
ext_op: in STD_LOGIC;
rd1: out STD_LOGIC_VECTOR(15 downto 0);
rd2: out STD_LOGIC_VECTOR(15 downto 0);
wd: in  STD_LOGIC_VECTOR(15 downto 0);
ext_imm: out STD_LOGIC_VECTOR(15 downto 0);
func: out STD_LOGIC_VECTOR(2 downto 0);
sa:out STD_LOGIC
);
end component;

component maincontrol is
port(
instr: in STD_LOGIC_VECTOR(15 downto 13);
regdest: out STD_LOGIC;
extop: out STD_LOGIC;
alusrc :out STD_LOGIC;
branch:out STD_LOGIC;
jump :out STD_LOGIC;
aluop :out STD_LOGIC_VECTOR(1 downto 0);
memwrite :out STD_LOGIC;
memtoreg :out STD_LOGIC;
regwrite: out STD_LOGIC;
brgtz: out STD_LOGIC);
end component;

signal enable_1 : STD_LOGIC := '0';
signal enable_2 : STD_LOGIC := '0'; 
signal iesire_mux : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');
signal pc: STD_LOGIC_VECTOR(15 downto 0);
signal ins: STD_LOGIC_VECTOR(15 downto 0);
type reg_array is array (0 to 15) of std_logic_vector (15 downto 0);
signal rf: reg_array;
signal regdst: STD_LOGIC;
signal regwrite: STD_LOGIC;
signal extop:STD_LOGIC;
signal rd1:STD_LOGIC_VECTOR(15 downto 0);
signal rd2: STD_LOGIC_VECTOR(15 downto 0);
signal func: STD_LOGIC_VECTOR(2 downto 0);
signal sa:STD_LOGIC;
signal ext_imm:STD_LOGIC_VECTOR(15 downto 0);
signal wd:STD_LOGIC_VECTOR(15 downto 0);
signal alusrc: STD_LOGIC;
signal branch: STD_LOGIC;
signal jump: STD_LOGIC;
signal aluop:STD_LOGIC_VECTOR(1 downto 0);
signal memwrite : STD_LOGIC;
signal memtoreg : STD_LOGIC;
signal brgtz:  STD_LOGIC;
signal extfunc: StD_LOGIC_VECTOR(15 downto 0);
signal extsa: StD_LOGIC_VECTOR(15 downto 0);
signal pcin:STD_LOGIC_VECTOR(15 downto 0);



begin


comp1: mpg port map(clk, btn(0),enable_1);
comp2: mpg port map(clk, btn(1),enable_2);
comp3 :SSD port map(an, cat, clk,iesire_mux(3 downto 0),
           iesire_mux(7 downto 4),iesire_mux(11 downto 8),
           iesire_mux(15 downto 12));
comp4: fetch port map(sw(0),x"0000",sw(1),x"0007", enable_1, enable_2, clk, pc, ins );
comp5: id port map(regwrite, ins, regdst, clk, enable_1,extop,rd1,rd2,wd,ext_imm, func,sa );
comp6: maincontrol port map(ins(15 downto 13),regdst,extop,alusrc,branch,jump,aluop,memwrite,memtoreg,regwrite,brgtz);

 wd<=rd1+rd2;
 extfunc<="0000000000000"& func;
 extsa<="000000000000000" & sa;
 
  with sw(7 DOWNTO 5) select 
      iesire_mux<=ins when "000",
      pcin when "001",
      rd1 when "010",
      rd2 when "011",
      wd when "100",
      ext_imm when "101",
      extfunc when "110",
      extsa when "111",
      (others=>'X')     WHEN OTHERS;
      led(10 downto 0)<=aluop & regdst &extop & alusrc &branch &brgtz &jump &memwrite & memtoreg & regwrite;

end testare_id_uc;

