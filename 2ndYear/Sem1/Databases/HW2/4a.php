<html>
<head>
<title>Exercitiul 4a</title>
</head>
<body bgcolor='white'>
<h1 font size=40 style="text-align:center;">Exercitiul 4</h1>
<h2 font size=30 style="background-color:powderblue;">Subpunctul a</h2>
<h3 font size=20 style="background-color:#FFF0F5;">Să se afișeze detaliile studioului și producătorului ce au produs filmul ‘Barry’
,2018.
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

$sql="select *
from film join persoana 
on id_persoana=id_producator
join studiou
on id_persoana=id_presedinte
where film.titlu = 'Barry' and film.an='08-Aug-2018';
";

$query=mysqli_query($conn,$sql);
if (!$query){
die("Interogare gresita!");
}
if (mysqli_num_rows($query) > 0){

?>
<table border="3" color='powderblue'>
<tr>
<th>gen</th>
<th>studiou</th>
<th>id_producator</th>
<th>durata</th>
<th>id_persoana</th>
<th>nume</th>
<th>adresa</th>
<th>sex</th>
<th>data_nasterii</th>
<th>castig_net</th>
<th>moneda</th>
<th>nume</th>
<th>adresa</th>
<th>id_presedinte</th>

</tr>

<?php

$cnt=0;
while ($row = mysqli_fetch_array($query)) {
echo "<tr><td>" . $row['gen'] . "</td><td>" . $row['studiou'] . "</td><td>" . $row['id_producator'] . "</td><td>" . $row['durata'] ."</td>
<td>" . $row['id_persoana'] ."</td><td>" . $row['nume'] ."</td><td>" . $row['adresa'] ."</td><td>" . $row['sex'] . "</td>
<td>" . $row['data_nasterii'] . "</td><td>" . $row['castig_net'] . "</td><td>" . $row['moneda'] . "</td><td>" . $row['nume'] . "</td>
<td>" . $row['adresa'] . "</td><td>" . $row['id_presedinte'] . "</td></tr>";
$cnt++;
}
echo "</table>";
}
else { echo "<center><b>Nu s-au gasit detaliile studioului și producătorului ce au produs filmul ‘Barry’.</b></center>"; }

mysqli_free_result($query);
mysqli_close($conn);

?>
<br></br>
 <form action="index.html" method="get" target="_blank">
         <button type="submit">Pagina principala</button>
      </form>

</body>
</html>