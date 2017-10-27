import com.sun.jmx.remote.internal.ArrayQueue
import java.util.*
import kotlin.collections.HashSet

val stateList = LinkedList<State>()

fun init() {
    stateList.clear()
    stateList.add(initState)

}

fun printResult(state: Stack<State>) {

}

var countSolve = 0

fun search(state: State, lastAction: Action?) {
//    val s = stateList.last

    if (checkFinish(state)) {
        countSolve += 1
        println("找到解 $countSolve")
        return
    }

    for (action in Action.values()) {
        if (lastAction != null) {
//        当前要做的动作不是上一次做的
            if (action.v == lastAction.v) {
                continue
//            当前的动作不与上一个动作互斥
//            比如上一个动作是：一个传教士过去，这次的动作不能是一个传教士回来
//            否则会导致死循环
            }
            if (action.v < 5) {
                if (action.v + 5 == lastAction.v)
                    continue
            } else {
                if (action.v - 5 == lastAction.v)
                    continue
            }
        }
        if (doAction(action, state)) {
            println(action.name)
            search(state, action)
        }
    }
}