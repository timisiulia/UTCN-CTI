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

Architecture mips_pipeline of test_env is

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
 
 component id is
 Port ( 
 regwrite: in STD_LOGIC;
 instr: in STD_LOGIC_VECTOR(15 downto 0);
 clk: in STD_LOGIC;
 en: in STD_LOGIC;
 ext_op: in STD_LOGIC;
 rd1: out STD_LOGIC_VECTOR(15 downto 0);
 rd2: out STD_LOGIC_VECTOR(15 downto 0);
 wa: in STD_LOGIC_VECTOR(2 downto 0);
 wd: in  STD_LOGIC_VECTOR(15 downto 0);
 ext_imm: out STD_LOGIC_VECTOR(15 downto 0);
 func: out STD_LOGIC_VECTOR(2 downto 0);
 sa:out STD_LOGIC;
 rt: out STD_LOGIC_VECTOR(2 downto 0);
 rd: out STD_LOGIC_VECTOR(2 downto 0)
 
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
 
 component EX is
Port (
  rd1: in STD_LOGIC_VECTOR(15 downto 0);
  rd2: in STD_LOGIC_VECTOR(15 downto 0);
  ext_imm: in STD_LOGIC_VECTOR(15 downto 0);
  sa: in STD_LOGIC;
  func: in STD_LOGIC_VECTOR(2 downto 0);
  PC_PLUS_1:  in STD_LOGIC_VECTOR(15 downto 0);
  alusrc:in STD_LOGIC;
  aluop: in STD_LOGIC_VECTOR(1 downto 0);
  bgtz: out STD_LOGIC;
  zero: out STD_LOGIC;
  alures: out STD_LOGIC_VECTOR(15 downto 0);
  branchaddress: out STD_LOGIC_VECTOR(15 downto 0);
  rt: in STD_LOGIC_VECTOR(2 downto 0);
  rd: in STD_LOGIC_VECTOR(2 downto 0);
  regdest: in STD_LOGIC;
  rwa: out STD_LOGIC_VECTOR(2 downto 0)
  
  
   );
 end component;
 
 component MEM is
 port(
 memwrite:in STD_LOGIC;
 aluresin: in STD_LOGIC_VECTOR(15 downto 0);
 rd2:in  STD_LOGIC_VECTOR(15 downto 0);
 clk: in STD_LOGIC;
 en:STD_LOGIC;
 memdata: out STD_LOGIC_VECTOR(15 downto 0);
 aluresout: out STD_LOGIC_VECTOR(15 downto 0)
 );
 end component;
 
 --componenta 1 si 2
 signal enable_1 : STD_LOGIC := '0';
 signal enable_2 : STD_LOGIC := '0'; 
 
 --componemta 3
 signal iesire_mux : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');
 
--componenta 4
 signal jump: STD_LOGIC;
 signal jumpadress: std_logic_vector(15 downto 0);
 signal pcsrc:std_logic;
 signal branchadress :  STD_LOGIC_VECTOR(15 downto 0);
 signal pcplus1: STD_LOGIC_VECTOR(15 downto 0);
 signal ins: STD_LOGIC_VECTOR(15 downto 0);


 --componenta 5
 signal regwrite: STD_LOGIC;
 signal extop:STD_LOGIC;
 signal rd1:STD_LOGIC_VECTOR(15 downto 0);
 signal rd2: STD_LOGIC_VECTOR(15 downto 0);
 signal wa: STD_LOGIC_VECTOR(2 downto 0);
 signal wd:STD_LOGIC_VECTOR(15 downto 0);
 signal ext_imm:STD_LOGIC_VECTOR(15 downto 0);
 signal func: STD_LOGIC_VECTOR(2 downto 0);
 signal sa:STD_LOGIC;
 signal rt: STD_LOGIC_VECTOR(2 downto 0);
 signal rd: STD_LOGIC_VECTOR(2 downto 0);

 --componenta 6
  signal regdst: STD_LOGIC;
  signal alusrc: STD_LOGIC;
  signal branch: STD_LOGIC;
  signal aluop:STD_LOGIC_VECTOR(1 downto 0);
   signal bgtz:std_logic;
  signal zero : std_logic;
  signal memwrite : STD_LOGIC;
  signal memtoreg : STD_LOGIC;
  signal brgtz:  STD_LOGIC;
  
  --componenta 7
  signal alures:  std_logic_vector(15 downto 0);
  signal regdest: STD_LOGIC;
  signal rwa: STD_LOGIC_VECTOR(2 downto 0);
  
  --componenta 8
   signal memdata: std_logic_vector(15 downto 0);
  signal aluresout:  std_logic_vector(15 downto 0);
  
  
  --alte semnale
  
   signal extfunc: StD_LOGIC_VECTOR(15 downto 0);
   signal extsa: StD_LOGIC_VECTOR(15 downto 0);
   signal pcin:STD_LOGIC_VECTOR(15 downto 0);
   signal aluresin:  std_logic_vector(15 downto 0);
  
  --semnale ifid
  signal instruction_ifid: StD_LOGIC_VECTOR(15 downto 0);
  signal pc_1_ifid: StD_LOGIC_VECTOR(15 downto 0);
  
  --semnale idex
  signal regdest_idex: std_logic;
  signal alusrc_idex: std_logic;
  signal branch_idex: std_logic;
  signal aluop_idex:STD_LOGIC_VECTOR(1 downto 0);
  signal memwrite_idex : STD_LOGIC;
  signal memtoreg_idex : STD_LOGIC;
  signal regwrite_idex: STD_LOGIC;
  signal brgtz_idex:  STD_LOGIC;
   signal rd1_idex: STD_LOGIC_VECTOR(15 downto 0);
  signal rd2_idex: STD_LOGIC_VECTOR(15 downto 0);
   signal ext_imm_idex:STD_LOGIC_VECTOR(15 downto 0);
  signal func_idex: STD_LOGIC_VECTOR(2 downto 0);
  signal sa_idex:STD_LOGIC;
  signal rt_idex: STD_LOGIC_VECTOR(2 downto 0);
  signal rd_idex: STD_LOGIC_VECTOR(2 downto 0);
  signal pc_1_idex: StD_LOGIC_VECTOR(15 downto 0);
  
  
  --semnale exmem
   signal bgtz_exmem:std_logic;
   signal zero_exmem : std_logic;
   signal alures_exmem:  std_logic_vector(15 downto 0);
   signal branchadress_exmem :  STD_LOGIC_VECTOR(15 downto 0);
   signal rwa_exmem: STD_LOGIC_VECTOR(2 downto 0);
   signal brgtz_exmem:  STD_LOGIC;
   signal rd_exmem: STD_LOGIC_VECTOR(2 downto 0);
   signal rd2_exmem: STD_LOGIC_VECTOR(15 downto 0);
   signal memwrite_exmem : STD_LOGIC;
   signal memtoreg_exmem : STD_LOGIC;
   signal regwrite_exmem: STD_LOGIC;
   
   --semnale memwb
    signal memtoreg_memwb : STD_LOGIC;
    signal regwrite_memwb: STD_LOGIC;
    signal alures_memwb:  std_logic_vector(15 downto 0);
    signal memdata_memwb: std_logic_vector(15 downto 0);
    signal rd_memwb: STD_LOGIC_VECTOR(2 downto 0);
      
     

 begin
 comp1: mpg port map(clk, btn(0),enable_1);
 comp2: mpg port map(clk, btn(1),enable_2);
 comp3 :SSD port map(an, cat, clk,iesire_mux(3 downto 0),
            iesire_mux(7 downto 4),iesire_mux(11 downto 8),
            iesire_mux(15 downto 12));
 comp4: fetch port map(jump,jumpadress,pcsrc,branchadress, enable_1, enable_2, clk, pcplus1, ins);
 comp5: id port map(regwrite, ins, clk, enable_1,extop,rd1,rd2,wa,wd,ext_imm, func,sa,rt,rd );
 comp6: maincontrol port map(instruction_ifid(15 downto 13),regdest,extop,alusrc,branch,jump,aluop,memwrite,memtoreg,regwrite,brgtz);
 comp7: EX port map(rd1_idex,rd2_idex,ext_imm_idex,sa_idex,func_idex,pc_1_idex,alusrc_idex,aluop_idex,bgtz,zero,alures,branchadress,rt,rd,regdest,rwa);
comp8: MEM port map(memwrite,alures_exmem,rd2_exmem,clk,enable_1,memdata, aluresout);

--memwb
process(clk)
begin
if clk'event and clk='1' then
if enable_1='1' then
memtoreg_memwb<=memtoreg_exmem;
regwrite_memwb<=regwrite_exmem;
alures_memwb<=aluresout;
memdata_memwb<=memdata;
rd_memwb<=rd_exmem;
end if;
end if;
end process;

--ifid
process(clk)
begin
if clk'event and clk='1' then
if enable_1='1' then
instruction_ifid<=ins;
pc_1_ifid<=pcplus1;
end if;
end if;
end process;


--idex
process(clk)
begin
if clk'event and clk='1' then
if enable_1='1' then
rd1_idex<=rd1;
rd2_idex<=rd2;
regdest_idex<=regdest;
ext_imm_idex<=ext_imm;
func_idex<=func;
sa_idex<=sa;
rd_idex<=rd;
rt_idex<=rt;
pc_1_idex<=pc_1_ifid;

alusrc_idex<=alusrc;
branch_idex<=branch;
memwrite_idex<=memwrite;
memtoreg_idex<=memtoreg;
regwrite_idex<=regwrite;


end if;
end if;
end process;


--exmem
process(clk)
begin
if clk'event and clk='1' then
if enable_1='1' then
branchadress_exmem<=branchadress;
memwrite_exmem<=memwrite;
memtoreg_exmem<=memtoreg;
regwrite_exmem<=regwrite;
zero_exmem<=zero;
alures_exmem<=alures;
rd_exmem<=rd;
rd2_exmem<=rd2_idex;


end if;
end if;
end process;

wd <= alures_memwb WHEN memtoreg_memwb = '0' else
      memdata_memwb;
pcsrc <= (brgtz and bgtz) or (zero and branch);
jumpadress <= pcplus1(15 downto 13)& ins(12 downto 0);

 with sw(7 DOWNTO 5) select 
     iesire_mux<=ins when "000",
     pcplus1 when "001",
     rd1_idex when "010",
     rd2_idex when "011",
     ext_imm_idex when "100",
     alures when "101",
     memdata when "110",
     wd when "111",
     (others=>'X')     WHEN OTHERS;
     led(10 downto 0)<=aluop & regdst &extop & alusrc &branch &brgtz &jump &memwrite & memtoreg & regwrite;
end mips_pipeline;





