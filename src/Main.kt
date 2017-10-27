fun staticTest() {
    println(doAction(Action.TWO_MONSTER_GO))
    println(doAction(Action.ONE_MONSTER_BACK))
    println(doAction(Action.TWO_MONSTER_GO))
    println(doAction(Action.ONE_MONSTER_BACK))
    println(doAction(Action.TWO_MONK_GO))
    println(doAction(Action.ONE_MONSTER_ONE_MONK_BACK))
    println(doAction(Action.TWO_MONK_GO))
    println(doAction(Action.ONE_MONSTER_BACK))
    println(doAction(Action.TWO_MONSTER_GO))
    println(doAction(Action.ONE_MONSTER_BACK))
    println(doAction(Action.TWO_MONSTER_GO))
    println("finish：")
    println(checkFinish())
}

fun main(argv: Array<String>) {
    staticTest()

}