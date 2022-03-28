library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity UNITATE_ARITM_LOG is
 Port ( 
        an:out STD_LOGIC_VECTOR(3 downto 0);
        cat:out STD_LOGIC_VECTOR(6 downto 0);
        clk : in STD_LOGIC;          
        btn : in STD_LOGIC_VECTOR (4 downto 0);
        sw : in STD_LOGIC_VECTOR (15 downto 0);
        led : out STD_LOGIC_VECTOR (15 downto 0)
 
 );
end UNITATE_ARITM_LOG;

architecture Behavioral of UNITATE_ARITM_LOG is
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
    digit1: in STD_LOGIC_VECTOR(3 downto 0);
    digit2: in STD_LOGIC_VECTOR(3 downto 0);
    digit3: in STD_LOGIC_VECTOR(3 downto 0)
    
 );
end component;
signal count :STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
signal en : STD_LOGIC := '0';
signal numarator : STD_LOGIC_VECTOR(1 downto 0);
signal add : STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
signal sub : STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
signal lsl :STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
signal lsr :STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
begin
componenta1:mpg port map (clk, btn(0),en);
 display:SSD port map(an, cat, clk,count(3 downto 0),
       count(7 downto 4),count(11 downto 8),
       count(15 downto 12));
  process( clk)
    begin
        if clk='1' and clk'event then
            if en='1' then
                if sw(0)='1' then
                    numarator<=numarator+1;
                    else
                    numarator<=numarator-1;
                  end if;
            end if;
        end if;
    
    end process;
  --alu
  process(sw)
      begin
         add<=("000000000000"& sw(3 downto 0))+("000000000000"& sw(7 downto 4));
         sub<=("000000000000"& sw(3 downto 0))-("000000000000"& sw(7 downto 4));
         lsl<="000000"& sw(7 downto 0) & "00";
         lsr<="0000000000"& sw(7 downto 2);
  end process;   
  
  --selectie mux
 process(numarator,add,sub,lsl,lsr)
      begin
          case numarator is
              when "00" => count<= add;
              when "01" =>  count <= sub;
              when "10" =>  count <= lsl;
              when "11" =>  count<= lsr;
              when others =>  count <= add;
           end case;   
      end process;
  --zero flag
          
         process(count)
          begin
              if count = "0000000000000000" then 
               led(7) <= '1';
               else 
               led(7) <= '0';
               end if;
          end process;



end Behavioral;
