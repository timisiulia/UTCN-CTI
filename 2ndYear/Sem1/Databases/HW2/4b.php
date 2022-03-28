<html>
<head>
<title>Exercitiul 4b</title>
</head>
<body bgcolor='white'>
<h1 font size=40 style="text-align:center;">Exercitiul 4</h1>
<h2 font size=30 style="background-color:powderblue;">Subpunctul b</h2>
<h3 font size=20 style="background-color:#FFF0F5;"> Care sunt perechile (id_actor, id_actor2) de actori de sex diferit ce au jucat în
același film? O pereche este unică în rezultat.
</h3>
<br></br>
</font>
<?php
include 'database.php';

$conn = mysqli_connect($db_host, $db_username, $db_pass, $db_name);

if (!$conn) {
echo "<br><br><br><a href=\"index.html\">home</a><br>";
die("Nu s-a putut conecta la BD: " . $conn->connect_error);
}

$sql="select a.titlu_film,a.id_actor,b.id_actor
from Distributie a join Distributie b 
on a.titlu_film = b.titlu_film
where (select sex  from persoana where id_persoana = a.id_actor) !=
(select sex  from persoana where id_persoana = b.id_actor);
";

$query=mysqli_query($conn,$sql);
if (!$query){
die("Interogare gresita!");
}
if (mysqli_num_rows($query) > 0){

?>
<table border="3" color='powderblue'>
<tr>
<th>titlu_film</th>
<th>id_actor</th>


</tr>

<?php

$cnt=0;
while ($row = mysqli_fetch_array($query)) {
echo "<tr><td>" . $row['titlu_film'] . "</td><td>" . $row['id_actor'] . "</td></tr>";
$cnt++;
}
echo "</table>";
}
else { echo "<center><b>Nu s-a gasit perechea de actori’.</b></center>"; }

mysqli_free_result($query);
mysqli_close($conn);

?>
<br></br>
 <form action="index.html" method="get" target="_blank">
         <button type="submit">Pagina principala</button>
      </form>


</body>
</html>