library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity test_env is
    Port (  an : out STD_LOGIC_VECTOR (3 downto 0);
          cat : out STD_LOGIC_VECTOR (6 downto 0);
           clk : in STD_LOGIC;          
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0)
          );
end test_env;
--architecture ROM of test_env is

--component mpg 
-- Port (
 --       clk: in STD_LOGIC;
 --       btn: in STD_LOGIC;
  --      enable: out STD_LOGIC
  --     );
--end component;

--component SSD 
 --Port ( 
  --   an: out STD_LOGIC_VECTOR(3 downto 0);
--     cat: out STD_LOGIC_VECTOR(6 downto 0);
--     clk: in STD_LOGIC;
--     digit0: in STD_LOGIC_VECTOR(3 downto 0);
 --    digit1:in STD_LOGIC_VECTOR(3 downto 0);
--     digit2:in STD_LOGIC_VECTOR(3 downto 0);
--     digit3:in STD_LOGIC_VECTOR(3 downto 0)
 --);
--end component;
--signal enable : STD_LOGIC := '0';
--signal final: STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
--signal count :STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
--type type_memory is array (0 to 7) of std_logic_vector(15 to 0);
--signal mem: type_memory :=(x"0001",x"0005",x"000A",others=>x"0000");

--begin

--comp1 : mpg port map(clk, btn(0),enable);
--comp2 :SSD port map(an, cat, clk,final(3 downto 0),
 --       final(7 downto 4),final(11 downto 8),
--        final(15 downto 12));
    
--process(clk)
--        begin
--            if clk='1' and clk'event then
--               if enable='1' then
--                    if sw(0)='1' then
--                        count<=count+1;
--                        else
--                       count<=count-1;
--                      end if;
--                end if;
--            end if;       
--    end process;

--final<=mem(conv_integer(count));
--end ROM;
--Register file
--architecture register_file of test_env is

--component mpg 
 --Port (
   --     clk: in STD_LOGIC;
  --      btn: in STD_LOGIC;
  --      enable: out STD_LOGIC
  --     );
--end component;

--component SSD 
 --Port ( 
  --   an: out STD_LOGIC_VECTOR(3 downto 0);
  --   cat: out STD_LOGIC_VECTOR(6 downto 0);
  --   clk: in STD_LOGIC;
  --   digit0: in STD_LOGIC_VECTOR(3 downto 0);
  --   digit1:in STD_LOGIC_VECTOR(3 downto 0);
 --    digit2:in STD_LOGIC_VECTOR(3 downto 0);
--     digit3:in STD_LOGIC_VECTOR(3 downto 0)
-- );
--end component;

--component reg_file 
  --Port ( 
 --         clk : in STD_LOGIC;
 --            ra1 : in STD_LOGIC_VECTOR (3 downto 0);
 --            ra2 : in STD_LOGIC_VECTOR (3 downto 0);
 --            wa : in STD_LOGIC_VECTOR (3 downto 0);
  --           wd : in STD_LOGIC_VECTOR (15 downto 0);
 --            wen : in STD_LOGIC;
 --            rd1 : out STD_LOGIC_VECTOR (15 downto 0);
 --            rd2 : out STD_LOGIC_VECTOR (15 downto 0)
 -- );
--end component;

--signal count :STD_LOGIC_VECTOR (3 downto 0) := (others =>'0');
--signal enable_1 : STD_LOGIC := '0';
--signal enable_2 : STD_LOGIC := '0';
--signal final : STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
--signal rd1 : STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
--signal rd2 : STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
--begin
--comp1: mpg port map(clk, btn(0),enable_1);
--comp2: mpg port map(clk, btn(0),enable_2);
--comp3 :SSD port map(an, cat, clk,final(3 downto 0),
--        final(7 downto 4),final(11 downto 8),
 --       final(15 downto 12));
--compReg_fileFROMRegFile: reg_file port map(clk,count ,count,count,final,enable_2,rd1,rd2);
--process(clk,btn(2))
  --  begin
  --      if btn(2) = '1' then 
  --        count <= (others =>'0');
  --      end if;
        
  --      if clk = '1' and clk' event then 
  --       if enable_1 = '1' then 
  --        if sw(0) = '1' then
  --         count <= count + 1;
  --        else
  --         count <= count - 1;          
  --        end if;
 --        end if;
 --       end if;    
               
--end process;
--final <= rd1 + rd2;
--end register_file;
--bram
--architecture BRAM of test_env is

--component mpg 
 --Port (
     --   clk: in STD_LOGIC;
    --    btn: in STD_LOGIC;
  --      enable: out STD_LOGIC
--       );
--end component;

--component SSD 
 --Port ( 
  --   an: out STD_LOGIC_VECTOR(3 downto 0);
  --   cat: out STD_LOGIC_VECTOR(6 downto 0);
  --   clk: in STD_LOGIC;
  --   digit0: in STD_LOGIC_VECTOR(3 downto 0);
   --  digit1:in STD_LOGIC_VECTOR(3 downto 0);
  --   digit2:in STD_LOGIC_VECTOR(3 downto 0);
  --   digit3:in STD_LOGIC_VECTOR(3 downto 0)
 --);
--end component;
--component bram is
--Port (
 --          clk : in STD_LOGIC;
   --        we : in STD_LOGIC;
  --         en : in STD_LOGIC;
 --          addr : in STD_LOGIC_VECTOR (3 downto 0);
 --          di : in STD_LOGIC_VECTOR (15 downto 0);
--           do : out STD_LOGIC_VECTOR (15 downto 0));
--end component;

--signal enable_1 : STD_LOGIC := '0';
--signal enable_2 : STD_LOGIC := '0'; 
--signal count : STD_LOGIC_VECTOR(3 downto 0):=(others=>'0');
--signal do : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');
--signal final : STD_LOGIC_VECTOR(15 downto 0):=(others=>'0');

--begin 

--comp1: mpg port map(clk, btn(0),enable_1);
--comp2: mpg port map(clk, btn(0),enable_2);
--comp3 :SSD port map(an, cat,clk,final(3 downto 0),
  --  final(7 downto 4),final(11 downto 8),
 --   final(15 downto 12));
--comp4: bram port map(clk,enable_2,'1',count,final,do);


--process(clk,btn(2))
  --      begin
  --          if btn(2) = '1' then 
  --            count <= (others =>'0');
  --          end if;
            
  --          if clk = '1' and clk' event then 
   --          if enable_1 = '1' then 
  --            if sw(0) = '1' then
  --             count <= count + 1;
  --            else
 --              count <= count - 1;          
 --             end if;
  --           end if;
  --          end if;                 
  --  end process;


--final <= do(13 downto 0) & "00";
   
--end bram;  


architecture instructiuni of test_env is 
type tROM is array (0 to 15) of std_logic_vector(15 to 0);
signal rom: tROM :=(B"000_000_000_001_0_000",-- X"0010" 0 add $1, $0, $0 # i = 0, contorul buclei 
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
                    others=>x"0000"
);
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
     digit1:in STD_LOGIC_VECTOR(3 downto 0);
     digit2:in STD_LOGIC_VECTOR(3 downto 0);
     digit3:in STD_LOGIC_VECTOR(3 downto 0)
 );
end component;

signal enable : STD_LOGIC := '0';
signal final: STD_LOGIC_VECTOR (15 downto 0) := (others =>'0');
signal pc:STD_LOGIC_VECTOR(3 downto 0):= (others =>'0');
begin
process(clk)
        begin
            if clk='1' and clk'event then
                if enable='1' then
                    if sw(0)='1' then
                        pc<=pc+1;
                        else
                        pc<=pc-1;
                      end if;
                end if;
            end if;       
    end process;
 
 comp1 : mpg port map(clk, btn(0),enable);
 comp2 :SSD port map(an, cat, clk,final(3 downto 0),
            final(7 downto 4),final(11 downto 8),
            final(15 downto 12));
            
 final <=rom(conv_integer(pc));
            
end instructiuni;


