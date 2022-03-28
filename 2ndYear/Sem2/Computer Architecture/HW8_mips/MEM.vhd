library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
entity MEM is
 Port (
 memwrite:in STD_LOGIC;
 aluresin: in STD_LOGIC_VECTOR(15 downto 0);
 rd2:in  STD_LOGIC_VECTOR(15 downto 0);
 clk: in STD_LOGIC;
 en:STD_LOGIC;
 memdata: out STD_LOGIC_VECTOR(15 downto 0);
 aluresout: out STD_LOGIC_VECTOR(15 downto 0)
  );
end MEM;

architecture Behavioral of MEM is
type reg_mem is array (0 to 31) of std_logic_vector (15 downto 0);
signal mem: reg_mem:=(others=>x"0000");
signal adresa:std_logic_vector (4 downto 0):=(others=>'0');
begin
process(clk)
begin
if rising_edge(clk) then
    if en='1' and memwrite='1' then
    MEM(conv_integer(adresa))<=rd2;
    end if;
   end if;
 end process;
memdata<=MEM(conv_integer(adresa));
aluresout<=aluresin;
adresa<=aluresin(4 downto 0);   


end Behavioral;