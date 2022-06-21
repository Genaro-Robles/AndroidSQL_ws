<?php

header('Content-Type: application/json');
include 'conexion.php';
$pdo = new Conexion();
if (isset($_GET['cat'])) {
    $sql = $pdo->prepare("SELECT * FROM productos WHERE idcategoria=:idcategoria");
    $sql->bindValue(':idcategoria', $_GET['cat']);
    $sql->execute();
    $sql->setFetchMode(PDO::FETCH_ASSOC);
    echo json_encode($sql->fetchAll(), JSON_FORCE_OBJECT);
    exit;
} else if (isset($_GET['pro'])) {
    $sql = $pdo->prepare("SELECT * FROM productos WHERE idproducto=:idproducto");
    $sql->bindValue(':idproducto', $_GET['pro']);
    $sql->execute();
    $sql->setFetchMode(PDO::FETCH_ASSOC);
    echo json_encode($sql->fetch(), JSON_FORCE_OBJECT);;
    exit;
} else {
    $sql = $pdo->prepare("SELECT * FROM categorias");
    $sql->execute();
    $sql->setFetchMode(PDO::FETCH_ASSOC);
    echo json_encode($sql->fetchAll(), JSON_FORCE_OBJECT);
    exit;
}

?>