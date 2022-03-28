library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
entity register_file_lab6 is
 Port (
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
end register_file_lab6;

architecture Behavioral of register_file_lab6 is
type reg_array is array (0 to 15) of std_logic_vector (15 downto 0);
signal rf: reg_array:=(others=>x"0000");
begin
process(clk)
begin
    if falling_edge(clk) then
        if en='1' and regwrite='1' then
            rf(conv_integer(wa))<=wd;
         end if;
        end if;
        
end process;
rd1<= rf(conv_integer(ra1));
rd2<= rf(conv_integer(ra2));



end Behavioral;
