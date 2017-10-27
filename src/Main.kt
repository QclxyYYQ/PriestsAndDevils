import Position.*

enum class Position(v: Int) {
    LOCAL(0),
    REMOTE(1)
}

/**
 * target: 动作的方向，参考点在本地，要过河对岸
 * 野蛮人数量的变化
 * 传教士数量的变化
 */
enum class Action constructor(val v: Int,
                              val target: Position,
                              val remoteMonsterEffect: Int, val remoteMonkEffect: Int,
                              val localMonsterEffect: Int, val localMonkEffect: Int) {
    ONE_MONSTER_GO(0, REMOTE, 1, 0, -1, 0),
    TWO_MONSTER_GO(1, REMOTE, 2, 0, -2, 0),
    ONE_MONK_GO(2, REMOTE, 0, 1, 0, -1),
    TWO_MONK_GO(3, REMOTE, 0, 2, 0, -2),
    ONE_MONSTER_ONE_MONK_GO(4, REMOTE, 1, 1, -1, -1),

    ONE_MONSTER_BACK(5, LOCAL, -1, 0, 1, 0),
    TWO_MONSTER_BACK(6, LOCAL, -2, 0, 2, 0),
    ONE_MONK_BACK(7, LOCAL, 0, -1, 0, 1),
    TWO_MONK_BACK(8, LOCAL, 0, -1, 0, 1),
    ONE_MONSTER_ONE_MONK_BACK(9, LOCAL, -1, -1, 1, 1)
}

data class State(var localMonster: Int, var localMonk: Int,
                 var remoteMonster: Int, var remoteMonk: Int,
                 var boatPosition: Position)

var globalState = State(
        localMonk = 3, localMonster = 3,
        remoteMonk = 0, remoteMonster = 0,
        boatPosition = LOCAL)

fun checkSafe(s: State): Boolean {
    if (s.localMonk != 0 && (s.localMonster > s.localMonk))
        return false
    if (s.remoteMonk != 0 && (s.remoteMonster > s.remoteMonk))
        return false
    return true
}

fun checkFinish(s: State = globalState): Boolean {
    if (s.localMonk == 0 && s.localMonster == 0) {
        return true
    }
    return false
}

/**
 * 当无效移动或者移动结果会导致游戏失败时，返回false
 */
fun doAction(a: Action): Boolean {
    println(a.name)
    //目标方向是目前所在的方向，属于无效的移动
    if (a.target == globalState.boatPosition)
        return false

    val state = globalState.copy()

    state.localMonster += a.localMonsterEffect
    state.localMonk += a.localMonkEffect
    state.remoteMonster += a.remoteMonsterEffect
    state.remoteMonk += a.remoteMonkEffect

    if (state.localMonster < 0 || state.localMonk < 0 || state.remoteMonk < 0 || state.remoteMonster < 0)
        return false

    if (checkSafe(state)) {
        state.boatPosition = a.target
        globalState = state
        return true
    }
    return false
}

fun main(argv: Array<String>) {

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
    println("完成：")
    println(checkFinish())
}