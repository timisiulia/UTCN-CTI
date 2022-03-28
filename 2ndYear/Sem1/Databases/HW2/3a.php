<html>
<head>
<title>Exercitiul 3a</title>
</head>
<body bgcolor='white'>
<h1 font size=40 style="text-align:center;">Exercitiul 3</h1>
<h2 font size=30 style="background-color:powderblue;">Subpunctul a</h2>
<h3 font size=20 style="background-color:#FFF0F5;">Să se găsească detaliile filmelor pentru copii ordonate crescător după an și
titlu. 
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
from film 
where gen= 'copii'
order by an ,titlu;
";

$query=mysqli_query($conn,$sql);
if (!$query){
die("Interogare gresita!");
}
if (mysqli_num_rows($query) > 0){

?>
<table border="4" color='white'>
<tr>
<th>titlu</th>
<th>an</th>
<th>gen</th>
<th>studiou</th>
<th>id_producator</th>
<th>durata</th>
</tr>

<?php

$cnt=0;
while ($row = mysqli_fetch_array($query)) {
echo "<tr><td>" . $row['titlu'] . "</td><td>" . $row['an'] . "</td><td>" . $row['gen'] . "</td><td>" . $row['studiou'] . "</td><td>" . $row['id_producator'] ."</td><td>" . $row['durata'] ."</td></tr>";
$cnt++;
}
echo "</table>";
}
else { echo "<center><b>Nu s-au gasit filme.</b></center>"; }

mysqli_free_result($query);
mysqli_close($conn);

?>
<br></br>
 <form action="index.html" method="get" target="_blank">
         <button type="submit">Pagina principala</button>
      </form>


</body>
</html>