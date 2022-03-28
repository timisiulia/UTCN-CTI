library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity mpg is
 Port ( 
        clk: in STD_LOGIC;
        btn: in STD_LOGIC;
        enable: out STD_LOGIC
 );
   
end mpg;

architecture Behavioral of mpg is
    signal q1 : STD_LOGIC := '0';
    signal q2 : STD_LOGIC := '0';
    signal q3 : STD_LOGIC := '0';
    signal k : STD_LOGIC_VECTOR (16 downto 0 ) := (others => '0');
begin
enable <= q2 AND (not q3);

    process (clk)
    begin
        if clk='1' and clk'event then
            k <= k + 1;
        end if;
    end process;

    process (clk)
    begin
        if clk'event and clk='1' then
            if k(15 downto 0) = "1111111111111111" then
                q1 <= btn;
            end if;
        end if;
    end process;

    
    process (clk)
        begin
            if clk'event and clk='1' then
                q2 <= q1;
            end if;
        end process;
        
    process (clk)
            begin
                if clk'event and clk='1' then
                    q3 <= q2;
                end if;
            end process;

end Behavioral;
