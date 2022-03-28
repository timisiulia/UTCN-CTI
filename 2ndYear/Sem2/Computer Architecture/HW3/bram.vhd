library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity bram is
    Port ( 
              clk : in STD_LOGIC;
               we : in STD_LOGIC;
               en : in STD_LOGIC;
               addr : in STD_LOGIC_VECTOR (3 downto 0);
               di : in STD_LOGIC_VECTOR (15 downto 0);
               do : out STD_LOGIC_VECTOR (15 downto 0)
          );
end bram;

architecture Behavioral of bram is

type bram_type is array (0 to 255) of std_logic_vector ( 15 downto 0);
signal BRAM:bram_type;

begin

process(clk)
begin
    if clk'event and clk='1' then 
        if en='1' then
            if we ='1' then
                bram(conv_integer(addr))<=di;
                do<=di;
            else 
                do<=bram(conv_integer(addr));
            end if;
        end if;
     end if;
end process;


end Behavioral;
