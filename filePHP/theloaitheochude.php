<?php 
	require "connect.php";

	class TheLoai{
		function TheLoai($idTheLoai, $idChuDe, $tenTheLoai, $hinhTheLoai){
			$this->IdTheLoai = $idTheLoai;
			$this->IdKeyChuDe = $idChuDe;
			$this->TenTheLoai = $tenTheLoai;
			$this->HinhTheLoai = $hinhTheLoai;
		}
	}

	$idchude = $_POST['idchude'];
	$query = "SELECT * FROM theloai WHERE IdChuDe = '$idchude'";
	$arrayTheLoai = array();
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayTheLoai, new TheLoai($row['IdTheLoai']
											,$row['IdChuDe']
											,$row['TenTheLoai']
											,$row['HinhTheLoai']));
	}
	echo json_encode($arrayTheLoai);
 ?>