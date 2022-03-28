library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity numarator_mpg_dcd is
Port ( 
           clk : in STD_LOGIC;          
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0)
      );
end numarator_mpg_dcd;

architecture Behavioral of numarator_mpg_dcd is
signal a: STD_LOGIC_VECTOR(2 downto 0):=(others =>'0');
component mpg 
 Port ( 
        clk: in STD_LOGIC;
        btn: in STD_LOGIC;
        enable: out STD_LOGIC
 );
end component;
signal en:STD_LOGIC:='0';
signal dcd:STD_LOGIC_VECTOR(7 downto 0):=(others =>'0');
begin
    componenta2:mpg port map (clk, btn(0),en);
    process( clk)
    begin
        if clk='1' and clk'event then
            if en='1' then
                if sw(0)='1' then
                    a<=a+1;
                    else
                    a<=a-1;
                  end if;
            end if;
        end if;
    
    end process;
    process(a)
            begin
            case a is 
             when "001" => dcd <= "00000010";
             when "010" => dcd <= "00000100";
             when "011" => dcd <= "00001000";
             when "100" => dcd <= "00010000";
             when "101" => dcd <= "00100000";
             when "110" => dcd <= "01000000";
             when "111" => dcd<= "10000000"; 
             when others => dcd<= "00000001";    
           end case;
            
        end process;
        
    led (7 downto 0) <= dcd;
    
end Behavioral;
