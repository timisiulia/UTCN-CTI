----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01/09/2022 09:37:22 AM
-- Design Name: 
-- Module Name: de_pus_pe_placa - Behavioral
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
use IEEE.std_logic_unsigned.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity de_pus_pe_placa is
  Port ( 
  Clk  : in  STD_LOGIC;
  Rst  : in  STD_LOGIC;
  Start : in std_logic;
  SelOp: in std_logic;
  SelAfis: in std_logic;
  x: inout std_logic_vector(7 downto 0);
  y: inout std_logic_vector(7 downto 0);
  An   : out STD_LOGIC_VECTOR (7 downto 0);
  Seg  : out STD_LOGIC_VECTOR (7 downto 0);
  selLed: out STD_LOGIC_VECTOR (3 downto 0)
  );
end de_pus_pe_placa;

architecture Behavioral of de_pus_pe_placa is
signal startD,selOpD,selAfisD,selNumOrRez: std_logic:='0';
signal rez,x_in,y_in,inDispl: std_logic_vector(31 downto 0):=(others => '0');
signal sel: std_logic_vector(3 downto 0):="0000";


begin
    filtru_buton_start: entity WORK.filtru_buton port map (
                    Clk => Clk,
                    Rst => Rst,
                    Din => Start,
                    Qout => startD);
    filtru_buton_selOp: entity WORK.filtru_buton port map (
                                        Clk => Clk,
                                        Rst => Rst,
                                        Din => SelOp,
                                        Qout => selOpD);
                                        
     filtru_buton_selAfis: entity WORK.filtru_buton port map (
                                                        Clk => Clk,
                                                        Rst => Rst,
                                                        Din => selAfis,
                                                        Qout => selAfisD);                                      
    x_in<=x"000000" & x;
    y_in<=x"000000" & y;                                       
    dut: entity work.blocOpLogice port map (Clk=>clk,Rst=>rst,Start=>startD,
                                            X=>x_in,Y=>y_in,selOp=>sel,rez=>rez);
    
    increment_selectie_op: process(selOpD)
        begin
            if rising_edge(selOpD) then 
                sel <= sel + 1 ;
            end if;
    end process increment_selectie_op;
    
    increment_selectie_selNumOrRez: process(selAfisD)
        begin
            if rising_edge(selAfisD) then 
                if selNumOrRez = '0' then
                   selNumOrRez <= '1';  
                else 
                   selNumOrRez <= '0';
                end if;
            end if;
    end process increment_selectie_selNumOrRez;                                         
                                                   
    inDispl <= rez when selNumOrRez = '1' 
                   else x"00"& x & x"00" & y;
    afisare: entity WORK.afisor port map(Clk=> clk ,Rst => rst ,Data => inDispl,An => An,Seg => Seg);
    selLed <= sel;
end Behavioral;
