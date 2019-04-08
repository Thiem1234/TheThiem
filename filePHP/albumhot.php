<?php 
	require "connect.php";

	$query = "SELECT DISTINCT * FROM album ORDER BY rand(".date("Ymd").") LIMIT 4";
	$dataAlbum = mysqli_query($conn, $query);
	
	class Album{
		function Album($idAlbum, $tenAlbum, $tenCasiAlbum, $hinhAlbum){
			$this->IdALbum = $idAlbum;
			$this->TenAlbum = $tenAlbum;
			$this->TenCasiAlbum = $tenCasiAlbum;
			$this->HinhAlbum = $hinhAlbum;
		}
	}
	$arrayAlbum = array();
	while ($row = mysqli_fetch_assoc($dataAlbum)) {
		array_push($arrayAlbum, new Album($row['IdAlbum']
										,$row['TenAlbum']
										,$row['TenCaSiAlbum']
										,$row['HinhAlbum']));
	}
	echo json_encode($arrayAlbum);

 ?>