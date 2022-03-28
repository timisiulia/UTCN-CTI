library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity fetch is
Port (
        jump: in STD_LOGIC;
        jump_address: in STD_LOGIC_VECTOR(15 downto 0);
        pcsrc: in STD_LOGIC;
        branchaddress: in STD_LOGIC_VECTOR(15 downto 0);
        en: in STD_LOGIC;
        rst: in STD_LOGIC;
        clk: in STD_LOGIC;
        pc_plus_1: out STD_LOGIC_VECTOR(15 downto 0);
        instruction: out STD_LOGIC_VECTOR(15 downto 0)
 );
end fetch;

architecture Behavioral of fetch is
signal q: STD_LOGIC_VECTOR(15 downto 0);
signal d: STD_LOGIC_VECTOR(15 downto 0);
signal outmux1: STD_LOGIC_VECTOR(15 downto 0);
type memory is array(0 to 32) of STD_LOGIC_VECTOR(15 downto 0);
signal memoryy: memory:=(B"000_000_000_001_0_000",-- X"0010" 0 add $1, $0, $0 # i = 0, contorul buclei 
                    B"001_000_100_0001010",--  X"220A" 1 addi $4, $0, 10 # se salveazã numãrul maxim de itera?ii (10)
                    B"000_000_000_010_0_000",--  X"0020" 2 add $2, $0, $0 # ini?ializarea indexului loca?iei de memorie
                    B"001_000_101_0000001",-- X"2281" 3 addi $5, $0, 1# prod=1 
                    B"100_001_100_0000111",--X"8607"  4 begin_loop: beq $1, $4, end_loop  # s-au fãcut 10 itera?ii? dacã da, salt în afara buclei 
                    B"010_010_011_0000101",--X"4985"  5 lw $3, arr_addr($2) # în $3 se aduce elementul curent din ?ir
                    B"001_011_011_1111111",--X"2DFF"  6 addi $3, $3, -1 # $3 = $3 - 1 SCADERE CU 1
                    B"011_010_011_0000101",--X"6985" 7 sw $3, arr_addr($2) # salvarea noii valori $3 în elementul curent din ?ir 
                    B"000_000_101_101_1_010",--X"02DA" 8 SLL $5, $5, 1# se inmulteste la produsul par?ial din $5 elementul curent
                    B"001_010_010_0000100",--X"2904" 9 addi $2, $2, 4 # indexul urmãtorului element din ?ir 
                    B"001_001_001_0000001",--X"2481" 10 addi $1, $1, 1 # i = i + 1, actualizarea contorului buclei
                    B"111_0000000000100",--X"E004" 11 j begin_loop # salt începutul buclei
                    B"011_101_000_0001111",--X"740F" 12   end_loop: sw $5, prod_addr($0)  # salvarea prod în memorie la adresa prod_addr  presupunem ca prod este la adresa 0F
                    others=>x"0000");
begin
process(clk)
begin
    if rising_edge(clk) then
    if rst='1' then
        q<=(others =>'0');
    elsif en='1' then
        q<=d;
     end if;
    end if;
end process;
--pt. jump-ul din mux 2
d <=  jump_address when jump = '1' else outmux1;
--iesirea din mux 1
outmux1 <=  branchaddress when pcsrc = '1' else
 q;
 --adresa urm instructiuni
pc_plus_1 <= q + 1;
--viitoarea instructiune
instruction <= memoryy(conv_integer(q)); 


end Behavioral;
