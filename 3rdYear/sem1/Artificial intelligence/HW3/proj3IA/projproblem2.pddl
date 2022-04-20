(define (problem problema1)
   (:domain ferma)
   (:objects Mihai boxa1 boxa2 boxa3 boxa-hrana)
   (:init 
        (fermier Mihai)
        (liber Mihai)
        (locatie-fermier boxa-hrana)
        (boxa boxa1)
        (boxa boxa-hrana)
        (sursahrana boxa-hrana)
        (animal boxa1)

        (de-hranit boxa1)

 )
   (:goal (and 
              (curatare boxa1)
              (odihnire Mihai)
              (liber Mihai)
               ))
               )
