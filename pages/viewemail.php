
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <style type="text/css">
        body{ font: 14px sans-serif; text-align: center; }
    </style>
</head>
<body>
   <h1>
   <?php

include "../controller/config.php"; 


$sql = "select emailsubject,description,semail,remail from email_mst";
$result = mysqli_query($db,$sql) or die("Bad query $sql");
// echo "Successful";?>
<h1>Show roles</h1>
	<table class='table table-bordered'>
			<thead class='thead-dark'>
				<tr>
					<th>Email Subject</th>
					<th>Description</th>
                    <th>Sender's Email</th>
                    <th>Reciever's Email</th>
				</tr>
			</thead>
			<tbody>
                <?php
while($row = mysqli_fetch_assoc($result)){
// echo "{$row['username']} {$row['uid']}";
// }
// if(mysqli_fetch_assoc($result)){
    ?>
    <tr>
    <td><?php echo "{$row['emailsubject']}" ?></td>
    <td><?php echo "{$row['description']}" ?></td>
    <td><?php echo "{$row['semail']}" ?></td>
    <td><?php echo "{$row['remail']}" ?></td>
    </tr>
    <!-- echo "{$row['emailsubject']}  {$row['description']} {$row['semail']} {$row['remail']} <br>"; -->
<!-- //    echo ' <input type="text" name="cllg" id="cllg" value='{$row['username']}'>'; -->
<!-- // } else{ -->
    <!-- // echo "cllg list empty"; -->
<?php
}
 ?>
 </tbody>
</table>
<?php 
// Close connection
mysqli_close($db);
?></h1>

</body>
</html>