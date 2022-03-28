<html>
<head>
<title>Exercitiul 6a</title>
</head>
<body bgcolor='white'>
<h1 font size=40 style="text-align:center;">Exercitiul 6</h1>
<h2 font size=30 style="background-color:powderblue;">Subpunctul a</h2>
<h3 font size=20 style="background-color:#FFF0F5;">Să se găsească pentru fiecare producător câte filme de gen ‘SF’ a produs.
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

$sql="select id_producator, count(*) from
Film
where id_producator = all(select id_producator from Film where gen='SF') and gen='SF'
group by id_producator;
";

$query=mysqli_query($conn,$sql);
if (!$query){
die("Interogare gresita!");
}
if (mysqli_num_rows($query) > 0){

?>
<table border="3" color='powderblue'>
<tr>
<th>id_producator</th>
<th>count(*)</th>
</tr>

<?php

$cnt=0;
while ($row = mysqli_fetch_array($query)) {
echo "<tr><td>" . $row['id_producator'] . "</td><td>" . $row['count(*)'] . "</td></tr>";
$cnt++;
}
echo "</table>";
}
else { echo "<center><b>Nu s-au gasit niciun film SF.</b></center>"; }

mysqli_free_result($query);
mysqli_close($conn);

?>
<br></br>
<form action="index.html" method="get" target="_blank">
         <button type="submit">Pagina principala</button>
      </form>


</body>
</html>