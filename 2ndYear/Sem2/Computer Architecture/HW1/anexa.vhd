library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity anexa is
Port ( 
           clk : in STD_LOGIC;          
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0)
      );
end anexa;

architecture Behavioral of anexa is

begin
led <= sw; --leagã switch-urile la leduri
an <= btn(3 downto 0); --anozii de la SSD la butoane
cat <= (others=>'0'); --catozii de la SSD activi

end Behavioral;
