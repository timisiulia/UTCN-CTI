library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity AFISOR is
Port (
        an:out STD_LOGIC_VECTOR(3 downto 0);
        cat:out STD_LOGIC_VECTOR(6 downto 0);
        clk : in STD_LOGIC;          
        btn : in STD_LOGIC_VECTOR (4 downto 0);
        sw : in STD_LOGIC_VECTOR (15 downto 0);
        led : out STD_LOGIC_VECTOR (15 downto 0)
        
 );
end AFISOR;

architecture Behavioral of AFISOR is
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
signal count :STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
signal en : STD_LOGIC := '0';

component mpg 
 Port ( 
        clk: in STD_LOGIC;
        btn: in STD_LOGIC;
        enable: out STD_LOGIC
 );
end component;
begin
componenta1:mpg port map (clk, btn(0),en);
    process( clk)
    begin
        if clk='1' and clk'event then
            if en='1' then
                if sw(0)='1' then
                    count<=count+1;
                    else
                    count<=count-1;
                  end if;
            end if;
        end if;
    
    end process;
    display:SSD port map(an, cat, clk,count(3 downto 0),
        count(7 downto 4),count(11 downto 8),
        count(15 downto 12));
        
    led <= count; 


end Behavioral;
