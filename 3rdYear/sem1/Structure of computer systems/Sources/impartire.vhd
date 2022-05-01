library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity impartire is
generic(n : natural:= 32);
  Port ( 
         clk: in std_logic;
         rst : in STD_LOGIC;
         start : in STD_LOGIC;
         x : in STD_LOGIC_VECTOR(2*n-1 downto 0);
         y : in STD_LOGIC_VECTOR(n-1 downto 0);
         a : out STD_LOGIC_VECTOR(n-1 downto 0);
         q : out STD_LOGIC_VECTOR(n-1 downto 0);
         Term : out STD_LOGIC);
end impartire;

architecture Behavioral of impartire is

signal an,loada,loadb,loadq,shla,shlq,subb,ovf,notan,selmuxin:  std_logic :='0';
signal outb,insum,outsum,outq: std_logic_vector(n-1 downto 0);
signal outa,ina: std_logic_vector(n downto 0);


begin

unitateDeControl: entity work.unitateaDeControl_imp generic map ( n => n) port map (
            start=>start,clk=>clk,rst=>rst,
            an=>an,selmuxin=>selmuxin,loada=>loada,loadb=>loadb,
            loadq=>loadq,shla=>shla,shlq=>shlq,subb=>subb,
            term=>term);

registrulb: entity work.nbit_register generic map(n => n) port map(
    d=>y,
    ce=>loadb,
    clk=>clk,
    rst=>rst,
    q=>outb);

adunaresauscadere:process(outb,subb)
  begin
  for i in 0 to n-1 loop
     insum(i) <= outB(i) xor subb;
  end loop;
end process adunaresauscadere;

suma: entity WORK.sumator_nbiti 
                generic map(n => n) 
                port map( 
                x=>outa(n-1 downto 0),
                y=>insum,
                tin=>subb,
                tout=>an,
                ovf=>ovf,
                s=>outsum );
                 

ina <= '0' & x(2*n-1 downto n) when selmuxin = '1' else  not(an) & outsum;

registrula: entity WORK.slrn_registru_de_deplasare_stanga 
       generic map(n => n+1) port map( 
                d => ina,
                sri=> outq(n-1),
                load=>loada,
                ce=>shla,
                clk=>clk,
                rst=>rst,
                q=>outa);
                
notan<= ina(n);

registrulq: entity WORK.slrn_registru_de_deplasare_stanga 
       generic map(n => n) port map( 
                d => x(n-1 downto 0),
                sri=> notan ,
                load=>loadq,
                ce=>shlq,
                clk=>clk,
                rst=>rst,
                q=>outq);                

q<=outq;
a<= outa(n-1 downto 0);   
end Behavioral;
