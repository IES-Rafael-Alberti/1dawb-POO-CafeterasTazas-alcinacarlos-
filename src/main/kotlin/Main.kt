/*
Desarrolla un programa con las siguientes clases:

- Clase Cafetera con atributos ubicacion (nombre de la sala o habitación dónde se encuentra), capacidad (la cantidad máxima de café que puede contener la cafetera) y cantidad (la cantidad actual de café
que hay en la cafetera).

Implementa, al menos, lo siguiente:

    * En la clase se debe establecer la capacidad máxima en 1000 (c.c.) y la cantidad actual en cero (cafetera vacía).
    * Constructor primario con la ubicacion.
    * Constructor secundario con la ubicación y la capacidad máxima de la cafetera. Inicializa la cantidad actual de café igual a la capacidad máxima.
    * Constructor secundario con la ubicacion, la capacidad máxima y la cantidad actual. Si la cantidad actual es mayor que la capacidad máxima de la cafetera, la ajustará al máximo.
    * llenar(): hace que la cantidad actual sea igual a la capacidad.
    * servirTaza(Taza): simula la acción de servir una taza con la capacidad que tenga la taza. Si la cafetera tiene café, llenará la taza y restará la cantidad servida en la cantidad de la cafetera. Si la cantidad actual de café “no alcanza” para llenar la taza, se sirve lo que quede. Actualizar la cantidad de la cafetera y aplicar el método adecuado de la taza.
    * vaciar(): pone la cantidad de café actual en cero.
    * agregarCafe(Int): añade a la cafetera la cantidad de café indicada. Por defecto añade 200 c.c. No se puede añadir más cantidad que su capacidad máxima, si ocurriera esta circunstancia, simplemente llenaríamos la cantidad de la cafetera a su capacidad máxima.
    * toString(): debe retornar por ejemplo "Cafetera(ubicación = Salón, capacidad = 1000 c.c., cantidad = 0 c.c.)"

- Clase Taza, que tendrá los atributos color, capacidad y cantidad.

Implementa, al menos, lo siguiente:

    * Un único constructor con el color (por defecto blanco) y la capacidad (por defecto 50 c.c.). La clase también contendrá un atributo con la cantidad que se establcerá a 0.
    * La propiedad cantidad debe modificarse para que si el valor que recibe es mayor que la capacidad de la taza, solo establezca el valor de dicha capacidad.
    * El método llenar() que establecerá la cantidad de la taza a su máxima capacidad.
    * El método "sobrecargado" llenar(Int) que establecerá la cantidad de la taza a la cantidad pasada como argumento al método.
    * Sobreescribir el método toString(), que debe retornar por ejemplo "Taza(color = BLANCO, capacidad = 50 c.c., cantidad = 30 c.c.)"

- Clase enumerada Color, que contendrá los colores disponibles: blanco, negro, gris, azul y verde.

En el programa principal se deben crear 3 cafeteras de capacidad 1000, 750 y 500 c.c. con 0, 750 y 200 c.c de cantidad respectivamente. 
Para crear cada cafetera, debéis utilizar un constructor diferente.
También debéis generar una lista de 20 tazas con la capacidad de 50, 75 y 100 de manear aleatoria.
Implementar el resto de comentarios TODO de la función main().

NOTA:
la sobrecarga de métodos se refiere a la capacidad de definir varios métodos en una clase con el mismo nombre pero con diferentes parámetros. 
Esto permite que una clase tenga varios métodos con la misma firma (nombre de método) pero con listas de parámetros diferentes.

La sobrecarga de métodos facilita la legibilidad del código y proporciona flexibilidad al programador al permitirle utilizar el mismo nombre de 
método para realizar operaciones similares pero con diferentes conjuntos de datos.

*/
class Taza(var color:Color = Color.Blanco, var capacidad: Int = 50){
    var cantidad = 0
        get() = field
        set(value) {
            if (value>capacidad){
                field = capacidad
            }else{
                field = value
            }
        }
    enum class Color{
        Blanco, Negro, Gris, Azul, Verde
    }
    fun llenar(){
        cantidad = capacidad
    }
    fun llenar(cantidad: Int){
        this.cantidad += cantidad
    }

    override fun toString(): String {
        return "Taza(color = $color, capacidad = $capacidad c.c., cantidad = $cantidad c.c.)"
    }
}

class Cafetera(var ubicacion: String){
    companion object{
        const val CAPACIDAD_MAXIMA = 1000
    }
    var capacidad:Int = CAPACIDAD_MAXIMA
    var cantidad = 0
        get() = field
        set(value) {
            if (value>capacidad){
                field = capacidad
            }else{
                field = value
            }
        }
    constructor(ubicacion:String, capacidad: Int): this(ubicacion){
        this.ubicacion = ubicacion
        this.capacidad = capacidad
        this.cantidad = CAPACIDAD_MAXIMA
    }

    constructor(ubicacion: String, cantidad: Int, capacidad:Int): this(ubicacion){
        if (cantidad >= CAPACIDAD_MAXIMA){
            this.cantidad = CAPACIDAD_MAXIMA
        } else{
            this.cantidad = cantidad
        }
        this.capacidad = capacidad
    }
    init{
        require(capacidad > 0 || capacidad < CAPACIDAD_MAXIMA) {"La capacidad tiene que estar entre 0 y 1000"}
    }
    fun llenar(){
        this.cantidad = this.capacidad
    }
    fun vaciar(){
        this.cantidad = 0
    }
    fun agragarCafe(cafe:Int = 200){
        if (cafe > CAPACIDAD_MAXIMA){
            this.cantidad = CAPACIDAD_MAXIMA
        }else{
            this.cantidad += cafe
        }
    }
    fun servirTaza(taza:Taza){
        if (taza.capacidad > cantidad){
            taza.llenar(cantidad)
            cantidad = 0
        }else{
            taza.llenar()
            cantidad -= taza.capacidad
        }
    }

    override fun toString(): String {
        return "Cafetera(ubicación = $ubicacion, capacidad = $capacidad c.c., cantidad = $cantidad c.c.)"
    }
}



fun main() {

    //TODO: Crear 3 cafeteras en la Sala, Cocina y Oficina
    var cafetera1 = Cafetera("Sala")
    var cafetera2 = Cafetera("Cocina", 750)
    var cafetera3 =Cafetera("Oficina", 200, 500)
    var cafeteras = mutableListOf(cafetera1, cafetera2, cafetera3)


    //TODO: Crear una lista de 20 tazas con capacidades aleatorias
    var listaTazas = mutableListOf<Taza>()
    val capacidadTazas = intArrayOf(50, 75, 100)
    for (i in 1..20){
        val taza = Taza(
            color = Taza.Color.values().random(),
            capacidad = capacidadTazas.random()
        )
        listaTazas.add(taza)
    }

    println("**********************************************")
    //TODO: Mostrar por pantalla el contenido de las 3 cafeteras y las tazas.
    cafeteras.forEach{ println(it)}
    listaTazas.forEach{ println(it)}

    println("**********************************************")
    println("Llenar la cafetera1 de café...")
    println("Vaciar la cafetera2...")
    println("Agregar café a la cafetera2 a la mitad de su capacidad...")
    println("Agregar 400 c.c. de café a la cafereta3...")

    //TODO: Llenar la cafetera1 de café.
    cafeteras[0].llenar()

    //TODO: Vaciar la cafetera2.
    cafeteras[1].vaciar()

    //TODO: Agregar café a la cafetera2 a la mitad de su capacidad.
    cafeteras[1].agragarCafe(cafeteras[1].capacidad/2)

    //TODO: Agregar 400 c.c. de café a la cafereta3
    cafeteras[2].agragarCafe(400)

    println("**********************************************")
    //TODO: Mostrar por pantalla el contenido de las 3 cafeteras
    cafeteras.forEach{ println(it)}

    println("**********************************************")
    println("Servir café en las tazas...")

    //TODO: Servir café en las tazas... siempre que haya café en la cafetera y en el orden cafetera1, cafetera2 y cafetera3.
    for (cafetera in cafeteras){
        for (taza in listaTazas){
            cafetera.servirTaza(taza)
        }
    }

    println("**********************************************")
    //TODO: Mostrar por pantalla el contenido de las 3 cafeteras y las tazas.
    cafeteras.forEach{ println(it)}
    listaTazas.forEach { println(it)}

}
