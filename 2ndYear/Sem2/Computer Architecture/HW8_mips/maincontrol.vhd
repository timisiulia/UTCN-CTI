library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
entity maincontrol is
 Port (
instr: in STD_LOGIC_VECTOR(15 downto 13);
regdest: out STD_LOGIC;
extop: out STD_LOGIC;
alusrc :out STD_LOGIC;
branch:out STD_LOGIC;
jump :out STD_LOGIC;
aluop :out STD_LOGIC_VECTOR(1 downto 0);
memwrite :out STD_LOGIC;
memtoreg :out STD_LOGIC;
regwrite: out STD_LOGIC;
brgtz: out STD_LOGIC
 );
end maincontrol;

architecture Behavioral of maincontrol is

begin

process(Instr)
begin
        regdest <='0';
        extop <='0';
        alusrc <='0';
        branch <='0';
        jump <='0';
        aluop <="00";
        memwrite <='0';
        memtoreg <='0';
        regwrite <='0';
        brgtz <='0';
        
             case Instr is
              --tip r
              when "000"=> 
              regdest <='1';
              extop <='0';
              alusrc <='0';
              branch <='0';
              jump <='0';
              aluop <="00";
              memwrite <='0';
              memtoreg <='0';
              regwrite <='1';
              brgtz <='0';
         
            --tip i, addi
              when"001"=>
              regdest <='0';
              extop <='1';
              alusrc <='1';
              branch <='0';
              jump <='0';
              aluop <="01";
              memwrite <='0';
              memtoreg <='0';
              regwrite <='1';
              brgtz <='0';
           
             --tip i, lw
               when"010"=>
               regdest <='0';
               extop <='1';
               alusrc <='1';
               branch <='0';
               jump <='0';
               aluop <="01";
               memwrite <='0';
               memtoreg <='1';
               regwrite <='1';
               brgtz <='0';
      
           
               --tip i, sw
                when"011"=>
                regdest <='0';
                extop <='1';
                alusrc <='1';
                branch <='0';
                jump <='0';
                aluop <="01";
                memwrite <='1';
                memtoreg <='0';
                regwrite <='0';
                brgtz <='0';
                         
                --tip i, beq
                when"100"=>
                regdest <='0';
                extop <='1';
                alusrc <='0';
                branch <='1';
                jump <='0';
                aluop <="10";
                memwrite <='0';
                memtoreg <='0';
                regwrite <='0';
                brgtz <='0';
                
                 --instructiune de tipul i, bgtz      
                 when "101"=> 
                 regdest <='0';
                 extop <='1';
                 alusrc <='0';
                 branch <='0';
                 jump <='0';
                 aluop <="10";
                 memwrite <='0';
                 memtoreg <='0';
                 regwrite <='0'; 
                 brgtz <='1';      
                   
                   --instructiune de tipul i, slti 
                  when "110"=> 
                  regdest <='0';
                  extop <='1';
                  alusrc <='1';
                  branch <='0';
                  jump <='0';
                  aluop <="11";
                  memwrite <='0';
                  memtoreg <='0';
                  regwrite <='1';
                  brgtz <='0';
                    
                    --instr de tipul j, jump
                    when "111"=>
                    regdest <='0';
                    extop <='0';
                    alusrc <='0';
                    branch <='0';
                    jump <='1';
                    aluop <="00";
                    memwrite <='0';
                    memtoreg <='0';
                    regwrite <='0';
                    brgtz <='0';
                           
        end case;
        
        
end process;

end Behavioral;
