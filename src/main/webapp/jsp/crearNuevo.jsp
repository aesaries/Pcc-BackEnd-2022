<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea Nuevo Registro</title>
    
    <jsp:include page="styles.jsp"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<!-- formulario -->
<div class="container">
<form class="m-5" action="CreateProductController" method="post">
    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="inputCodigo">Codigo</label>
        <input type="text" class="form-control" id="inputCodigo" placeholder="Codigo" name="Codigo">
      </div>
      <div class="form-group col-md-6">
        <label for="inputTitulo">Titulo</label>
        <input type="text" class="form-control" id="inputTitulo" placeholder="Titulo" name="Titulo">
      </div>
    </div>
    <div class="form-group col-md-6">
      <label for="inputAddress">Precio</label>
      <input type="number" class="form-control" id="inputPrecio" placeholder="Precio" min="0" name="Precio">
    </div>
    <div class="form-group col-md-6">
      <label for="inputFecha">Fecha de Alta</label>
      <input type="date" class="form-control" id="inputFecha" placeholder="fecha de alta del producto" name="Fecha">
    </div>
    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="inputCity">Autor</label>
        <input type="text" class="form-control" id="inputAutor" placeholder="Autor del libro" name="Autor">
      </div>
    </div>
    <div class="form-group col-md-6">
        <label for="inputAddress">Imagen</label>
        <input type="file" class="form-control" id="inputImagen" placeholder="Agregue una imagen" accept=".jpg,.gif,.png"  name="Imagen">
      </div>
    
    <button type="submit" class="btn btn-primary m-3">Agregar</button>
  </form>
</div>


    

<jsp:include page = "script.jsp"/>

</body>
</html>
