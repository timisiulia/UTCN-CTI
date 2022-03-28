library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity id is
Port ( 
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
end id;

architecture Behavioral of id is
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
signal mux : std_logic_vector(2 downto 0) ;
begin
 mux<=instr(9 downto 7) when regdst='0' else instr(6 downto 4);
comp1: register_file_lab6 port map(clk, instr(12 downto 10),instr(9 downto 7),mux, wd,en,rd1, rd2, regwrite);
ext_imm <= "000000000"&instr(6 downto 0) WHEN ext_op ='0' ELSE
            "111111111"&instr(6 downto 0);
func<=instr(2 downto 0);
sa<=instr(3);

end Behavioral;
