<?php 
	require "connect.php";

	class Album{
		function Album($idAlbum, $tenAlbum, $tenCasiAlbum, $hinhAlbum){
			$this->IdALbum = $idAlbum;
			$this->TenAlbum = $tenAlbum;
			$this->TenCasiAlbum = $tenCasiAlbum;
			$this->HinhAlbum = $hinhAlbum;
		}
	}

	$query = "SELECT * FROM album";
	$data = mysqli_query($conn, $query);
	$arrayAlbum = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayAlbum, new Album($row['IdAlbum']
										,$row['TenAlbum']
										,$row['TenCaSiAlbum']
										,$row['HinhAlbum']));
	}
	echo json_encode($arrayAlbum);
 ?>