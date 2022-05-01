----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/03/2021 08:12:20 PM
-- Design Name: 
-- Module Name: bistabilD_fd - Behavioral
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

entity bistabilD_fd is
    Port ( d : in STD_LOGIC;
           ce : in STD_LOGIC;
           clk : in STD_LOGIC;
           rst : in STD_LOGIC;
           q : out STD_LOGIC);
end bistabilD_fd;

architecture Behavioral of bistabilD_fd is

begin

bistabil: process(d,ce,rst,clk)
begin
    if rising_edge(clk) then 
        if rst = '1' then 
            q <= '0';
         elsif ce = '1' then 
            q <= d;
        end if;
        
    end if;
end process bistabil;


end Behavioral;
