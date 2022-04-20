;Aplcatie de plannning hranire la ferma. Exista boxe, in care pot fi animale sau in care pot exista si surse de hrana. 
;Fermierii trebuie sa hraneasca toate animalele, iar un fermier poate hrani un singur animal o data

(define (domain ferma)
  (:requirements :strips) 
  (:predicates 	(de-hranit ?boxa); animalul dn boxa boxa trebuie hranit
                (de-adapat ?boxa); animalul dn boxa boxa trebuie hranit
                (sursahrana ?boxa) ; boxa e sursahrana
                (sursaapa  ?boxa)
	       	    (fermier ?fermier) ; fermier e fermier
                (boxa ?boxa) ; e boxa 
		        (animal ?boxa) ; la boxa boxa exista un animal 
		        (luat-hrana ?fermier) ; la fermierul fermier exista hrana
		        (luat-apa ?fermier) ; la fermierul fermier exista apa
		        (hranit ?boxa) ; animalul din boxa boxa este hranit 
		        (adapat ?boxa) ; animalul din boxa boxa este adapat
		        (liber ?fermier) ;fermierul este liber
		        (locatie-fermier ?boxa) ;fermierul e la boxa 
		        (boxa-murdara ?boxa) ;boxa e murdara
		        (boxa-curata ?boxa) ;boxa e curata
		        (curatare ?boxa)
		        (odihnire ?fermier)
		        (mers-acasa ?fermier)
		        
	           
    ) 

(:action luare-hrana ; fermierul e instiintat daca un animal are nevoie de hrana
  :parameters
   (?boxa ?fermier)
  :precondition
   (and (fermier ?fermier) (boxa ?boxa) (sursahrana ?boxa) (locatie-fermier ?boxa))
  :effect
   (and (not (liber ?fermier)) (luat-hrana ?fermier) ))


(:action hranire ;hranire animal
  :parameters (?fermier ?boxa)
  :precondition
   (and (luat-hrana ?fermier) (animal ?boxa) (de-hranit ?boxa)(locatie-fermier ?boxa))
  :effect
   (and (hranit ?boxa) (not(de-hranit ?boxa)) (not (luat-hrana ?fermier)) (boxa-murdara ?boxa)))


(:action deplasare ;fermierul duce mancarea la animal
  :parameters
   (?boxa-plecare ?boxa-sosire)
  :precondition
   (and  (boxa ?boxa-plecare) (boxa ?boxa-sosire) (locatie-fermier ?boxa-plecare))
  :effect
   (and (not (locatie-fermier ?boxa-plecare)) (locatie-fermier ?boxa-sosire)))


(:action curatare
 :parameters (?fermier  ?boxa)
 :precondition
 (and (hranit ?boxa) (not(de-hranit ?boxa)) (not (luat-hrana ?fermier)) (boxa-murdara ?boxa))
 :effect
 (and (curatare ?boxa ) (not( boxa-murdara ?boxa)) (sursaapa ?boxa) (not(odihnire ?fermier))))
 
 
(:action odihnire
 :parameters(?fermier ?boxa)
 :precondition
 (and (curatare ?boxa ) (not( boxa-murdara ?boxa)) (sursaapa ?boxa) (not(odihnire ?fermier)))
 :effect
 (and (odihnire ?fermier)))
 
 
(:action luare-apa ;fermierul e instiintat daca un animal are nevoie de apa
 :parameters (?fermier ?boxa)
  :precondition
   (and (fermier ?fermier) (boxa ?boxa)  (sursaapa ?boxa) (curatare ?boxa ) (not( boxa-murdara ?boxa)) )
  :effect
   (and (not (liber ?fermier)) (luat-apa ?fermier)))
   

(:action adapare ;hranire animal
  :parameters (?fermier ?boxa)
  :precondition
   (and (luat-apa ?fermier) (animal ?boxa) (locatie-fermier ?boxa))
  :effect
   (and (adapat ?boxa) (mers-acasa ?fermier)))
   
   
(:action mersAcasa ;hranire animal
  :parameters (?fermier ?boxa)
  :precondition
   (and (adapat ?boxa) (odihnire ?fermier) (locatie-fermier ?boxa) (mers-acasa ?fermier))
  :effect
   (and (liber ?fermier) ))  

)
