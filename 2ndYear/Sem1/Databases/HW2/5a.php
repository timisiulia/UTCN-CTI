<html>
<head>
<title>Exercitiul 5a</title>
</head>
<body bgcolor='white'>
<h1 font size=40 style="text-align:center;">Exercitiul 5</h1>
<h2 font size=30 style="background-color:powderblue;">Subpunctul a</h2>
<h3 font size=20 style="background-color:#FFF0F5;"> ) Să se găsească numele actriței cel mai puțin plătită.
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

$sql="select nume 
from persoana 
where sex = 'F' and 
castig_net < ANY(select castig_net from persoana where  sex ='F')

";

$query=mysqli_query($conn,$sql);
if (!$query){
die("Interogare gresita!");
}
if (mysqli_num_rows($query) > 0){

?>
<table border="3" color='powderblue'>
<tr>
<th>nume</th>
</tr>

<?php

$cnt=0;
while ($row = mysqli_fetch_array($query)) {
echo "<tr><td>" . $row['nume'] . "</td></tr>";
$cnt++;
}
echo "</table>";
}
else { echo "<center><b>Nu s-au gasit actrita cea mai putin platita.’.</b></center>"; }

mysqli_free_result($query);
mysqli_close($conn);

?>
<br></br>
<form action="index.html" method="get" target="_blank">
         <button type="submit">Pagina principala</button>
      </form>


</body>
</html>