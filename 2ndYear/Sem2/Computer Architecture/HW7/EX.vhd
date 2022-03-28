library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity EX is
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
 branchaddress: out STD_LOGIC_VECTOR(15 downto 0)
 
  );
end EX;

architecture Behavioral of EX is
signal zero_ex:STD_LOGIC;
signal A: STD_LOGIC_VECTOR(15 downto 0);
signal B:STD_LOGIC_VECTOR(15 downto 0);
signal C: STD_LOGIC_VECTOR(15 downto 0);
signal aluctrl:STD_LOGIC_VECTOR(2 downto 0);

begin
ALUControl: process(aluop, func)
            begin
                case aluop is 
                when "00" =>                           
                             case func is 
                                when "000" => aluctrl <= "000";
                                when "001" => aluctrl <= "001";
                                when "010" => aluctrl <= "010";
                                when "011" => aluctrl <= "011";
                                when "100" => aluctrl <= "100";
                                when "101" => aluctrl <= "101";
                                when "110" => aluctrl <= "110";
                                when "111" => aluctrl <= "111";
                            end case;                            
                when "01" => aluctrl <= "000";
                when "10" =>aluctrl <= "001";
                when "11" =>aluctrl <= "111";
                when others => aluctrl <= (others => 'X'); 
                end case;               
end process;

A<=rd1;

B <= rd2 WHEN alusrc ='0' ELSE
            ext_imm;
process (aluctrl, A, B, sa)
begin
    case aluctrl is
        when "000" => C <= A + B;
        when "001" => C <= A - B;
        when "010" => if sa = '1' then 
                                  C <=B(14 downto 0) & '0';
                            else
                                  C <=B;  
                            end if;    
      
        when "011" => if sa = '1' then 
                                  
                                    C <= '0' & B(15 downto 1); 
                            else
                                     C <= B;
                            end if;      
        when "100" => C <= A and B;
        when "101" => C <= A or B;
        when "110" => C <= A xor B;
        when "111" => if signed(A) < signed(B) then C<=X"0001";
                      else C <= X"0000";
                       end if;
    end case;
end process;


process(C)
begin 
 
  if C = x"0000" then 
                    zero_ex<='1';
  else 
                    zero_ex<='0';
  end if;
       
end process;                     
bgtz<=not(C(15)) and zero_ex;
zero <= zero_ex;
branchaddress <= ext_imm +  PC_PLUS_1;
alures <= C;

end Behavioral;
