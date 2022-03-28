<html>
<head>
<title>Exercitiul 3b</title>
</head>
<body bgcolor='white'>
<body bgcolor='white'>
<h1 font size=40 style="text-align:center;">Exercitiul 3</h1>
<h2 font size=30 style="background-color:powderblue;">Subpunctul b</h2>
<h3 font size=20 style="background-color:#FFF0F5;">Să se găsească detaliile persoanelor cu câștig net peste 1000000 USD.
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
from Persoana 
where castig_net > 1000000 
and moneda = 'USD'
";

$query=mysqli_query($conn,$sql);
if (!$query){
die("Interogare gresita!");
}
if (mysqli_num_rows($query) > 0){

?>
<table border="3" color='powderblue'>
<tr>
<th>id_persoana</th>
<th>nume</th>
<th>adresa</th>
<th>sex</th>
<th>data_nasterii</th>
<th>castig_net</th>
<th>moneda</th>

</tr>

<?php

$cnt=0;
while ($row = mysqli_fetch_array($query)) {
echo "<tr><td>" . $row['id_persoana'] . "</td><td>" . $row['nume'] . "</td><td>" . $row['adresa'] . "</td><td>" . $row['sex'] ."</td><td>" . $row['data_nasterii'] ."</td><td>" . $row['castig_net'] ."</td><td>" . $row['moneda'] ."</td></tr>";
$cnt++;
}
echo "</table>";
}
else { echo "<center><b>Nu s-au gasit persoane cu câștig net peste 1000000 USD .</b></center>"; }

mysqli_free_result($query);
mysqli_close($conn);

?>
<br></br>
 <form action="index.html" method="get" target="_blank">
         <button type="submit">Pagina principala</button>
      </form>


</body>
</html>