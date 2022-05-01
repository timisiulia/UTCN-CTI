----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01/07/2022 03:06:31 PM
-- Design Name: 
-- Module Name: blocOpLogice - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity blocOpLogice is
 Port (             
           clk: in std_logic;
           rst : in STD_LOGIC;
           start : in STD_LOGIC;
           x : in STD_LOGIC_VECTOR (31 downto 0);
           y: in std_logic_vector(31 downto 0);
           selOp:in std_logic_vector(3 downto 0);
           rez: inout std_logic_vector(31 downto 0)
           );
end blocOpLogice;

architecture Behavioral of blocOpLogice is
signal shiftx,shifty,rezAnd,rezOr,rezXor,rezAdunare,rezScadere,a_rezInmultire,q_rezInmultire,a_rezImpartire,q_rezImpartire:std_logic_vector(31 downto 0):=(others =>'0');
signal tin,tout,ovf,term: std_logic:='0';
signal ximp:std_logic_vector(63 downto 0):=(others =>'0');
begin 
ximp<=x"00000000"&x;
opand: entity WORK.operatiaAND port map(x=>x,y=>y,rez=>rezAnd);
opor: entity WORK.operatiaOr port map(x=>x,y=>y,rez=>rezOr);
opxor: entity WORK.operatiaXor port map(x=>x,y=>y,rez=>rezXor);
opadunare: entity WORK.sumator_32biti port map(x=>x,y=>y ,
                    tin=>tin,tout=>tout,ovf=>ovf,sum=>rezAdunare);
opscadere: entity WORK.scazator_32biti port map(x=>x,y=>y ,
                    tin=>tin,tout=>tout,ovf=>ovf,sum=>rezScadere);
opinmultire:entity work.Booth port map (Clk => clk,Rst=>rst,Start=>start,
                    X=>x,Y=>y,A=>a_rezInmultire,Q=>q_rezInmultire,Term=>term);
opimpartire: entity work.impartire port map (Clk => clk,Rst=>rst,Start=>start,
                                        X=>ximp,Y=>y,A=>a_rezImpartire,Q=>q_rezImpartire,Term=>term);                                       


ptinitshiftare:process(clk,start)
begin
        if rising_edge(start) then
            shiftx<=x;
            shifty<=y;
         end if; 

end process;
selectieop: process(selOp,clk)
begin
if rising_edge(clk) then
case selOp is
when "0000" => rez <= rezAnd; --cod pt and
when "0001" => rez <= rezOr; --cod pt or
when "0010" => rez <= rezXor; --cod pt xor
when "0011" => rez <= not(x); -- cod pt not x
when "0100" => rez <= not(y); -- cod pt not y
when "0101" =>  -- shiftare x la dreapta
                rez <='0' & x(31 downto 1);
when "0110" =>-- shiftare y la dreapta
                rez <='0' & y(31 downto 1); 
when "0111" =>-- shiftare x la stanga
                rez <=x(30 downto 0)& '0';                 
when "1000" =>-- shiftare y la stanga;  
                rez <=y(30 downto 0)& '0'; 
when "1001" => rez<= rezAdunare;--adunare
when "1010" => rez<= rezScadere;--scadere
when "1011" => rez<=q_rezInmultire;
when "1100" => rez<=a_rezImpartire(15 downto 0)&q_rezImpartire(15 downto 0);
when others => rez<=x"00000000";
end case;
end if;
end process selectieop;
end Behavioral;