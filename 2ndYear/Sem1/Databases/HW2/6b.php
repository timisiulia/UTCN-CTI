<html>
<head>
<title>Exercitiul 6b</title>
</head>
<body bgcolor='white'>
<h1 font size=40 style="text-align:center;">Exercitiul 6</h1>
<h2 font size=30 style="background-color:powderblue;">Subpunctul b</h2>
<h3 font size=20 style="background-color:#FFF0F5;">Să se găsească câștigul minim și câștigul maxim pentru președinții de studiouri. 
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

$sql="select min(p.castig_net) salariu_minim, max(p.castig_net) salariu_maxim
from Studiou s join Persoana p
on s.id_presedinte=p.id_persoana;
";

$query=mysqli_query($conn,$sql);
if (!$query){
die("Interogare gresita!");
}
if (mysqli_num_rows($query) > 0){

?>
<table border="3" color='powderblue'>
<tr>
<th>salariu_minim</th>
<th>salariu_maxim</th>
</tr>

<?php

$cnt=0;
while ($row = mysqli_fetch_array($query)) {
echo "<tr><td>" . $row['salariu_minim'] . "</td><td>" . $row['salariu_maxim'] . "</td></tr>";
$cnt++;
}
echo "</table>";
}
else { echo "<center><b>Nu s-au gasit niciun salariu care sa satsifaca cerinta.</b></center>"; }

mysqli_free_result($query);
mysqli_close($conn);

?>
<br></br>
<form action="index.html" method="get" target="_blank">
         <button type="submit">Pagina principala</button>
      </form>


</body>
</html>