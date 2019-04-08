<?php 
	require "connect.php";

	$query = "SELECT DISTINCT * FROM playlist ORDER BY rand(" . date('Ymd').") LIMIT 3 ";
	/*if ($query) {
		echo "OK";
	}else{
		echo "Fail";
	}*/
	class PlayListToday{
		function PlayListToday($idPlayList, $ten, $hinh, $icon){
			$this->IdPlayList = $idPlayList;
			$this->Ten = $ten;
			$this->HinhPlayList = $hinh;
			$this->Icon = $icon;

		}
	}

	$arrayPlayListForToday = array();
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayPlayListForToday, new PlayListToday($row['IdPlayList']
															,$row['Ten'],
															,$row['HinhNen']
															,$row['HinhIcon']));
	}
	echo json_encode($arrayPlayListForToday);
?>