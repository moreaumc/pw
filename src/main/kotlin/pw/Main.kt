package pw

fun pipeline(init: Pipeline.() -> Unit): Pipeline {
    val pl = Pipeline()
    pl.init()
    return pl
}



class Pipeline() : CompositeAstNode() {

    fun name(name : String) : Name {
        val name = Name(name)
        children.add(name)
        return name
    }

    fun stage(name: String, init: Stage.() -> Unit) = initNode(Stage(name), init)

}

class Name(val name : String) : AstNode()

class Stage(val name : String) : CompositeAstNode() {
    fun step(name: String, init: Step.() -> Unit) = initNode(Step(name), init)
}

class Step(val name : String) : AstNode()

abstract class AstNode() {

}

abstract class CompositeAstNode() : AstNode()  {
    val children = arrayListOf<AstNode>()

    protected fun <T : AstNode> initNode(node: T, init: T.() -> Unit): T {
        node.init()
        children.add(node)
        return node
    }
}

fun main(args: Array<String>) {

    val pl = pipeline  {
        name("test pipeline")
        stage("build") {
            step("first step name") {}
        }
    }
    println(pl)
}

