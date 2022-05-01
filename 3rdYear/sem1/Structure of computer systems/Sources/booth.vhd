library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity booth is
generic(n : natural:= 32);
  Port ( 
         clk: in std_logic;
         rst : in STD_LOGIC;
         start : in STD_LOGIC;
         x : in STD_LOGIC_VECTOR(n-1 downto 0);
         y : in STD_LOGIC_VECTOR(n-1 downto 0);
         a : out STD_LOGIC_VECTOR(n-1 downto 0);
         q : out STD_LOGIC_VECTOR(n-1 downto 0);
         Term : out STD_LOGIC);
end booth;

architecture Behavioral of booth is
signal loada,loadb,loadq : STD_LOGIC;
signal rsta,rstqm : STD_LOGIC;
signal subb : STD_LOGIC;
signal ovf: STD_LOGIC;
signal tout : STD_LOGIC;
signal shraq :STD_LOGIC;
signal qm1 :STD_LOGIC;

signal outb :STD_LOGIC_VECTOR(n-1 downto 0);
signal outa :STD_LOGIC_VECTOR(n-1 downto 0);
signal outq :STD_LOGIC_VECTOR(n-1 downto 0);
signal inadd :STD_LOGIC_VECTOR(n-1 downto 0);
signal outsum :STD_LOGIC_VECTOR(n-1 downto 0);

begin

registrulb: entity WORK.nbit_register 
          generic map ( n => n) 
          port map (D => X ,
                    Ce => LoadB,
                    Clk => Clk,
                    Rst => '0',                                        
                    Q => outB);

sumasauadunare:process(outB,SubB)
  begin
  for i in 0 to n-1 loop
     inAdd(i) <= outB(i) xor SubB;
  end loop;
end process sumasauadunare;   

suma: entity WORK.sumator_nbiti 
                generic map(n => n) 
                port map( outa,inadd,subb,tout,ovf,outsum);
                          
registrula: entity WORK.srrn_registru_de_deplasare 
       generic map(n => n) 
       port map (outsum,outa(7), Loada,shraq, clk,Rsta, outa);
       
registrulq: entity WORK.srrn_registru_de_deplasare
          generic map(n => n) 
             port map( y, outa(0), loadq,shraq,clk, rst,outq);
      
registrulqminus1: entity WORK.bistabilD_fd 
       port map(outq(0), shraq, clk, rstqm,qm1); 
                
control: entity WORK.unitateaDeControl generic map (n => n) 
           port map (start, clk, rst, outq(0), qm1, loada,
                      loadb, loadq, shraq, rsta,rstqm,subb,term);                 
                    
A <= outa;
Q <= outq;
end Behavioral;
