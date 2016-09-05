package pw

fun pipeline(name : String, init: Pipeline.() -> Unit): Pipeline {
    val pl = Pipeline(name)
    pl.init()
    return pl
}



class Pipeline(val name: String) : CompositeAstNode() {

    fun stage(name: String, init: Stage.() -> Unit) = initNode(Stage(name), init)

    override fun toString(): String{
        return "Pipeline(name='$name')"
    }

}

class Stage(val name : String) : CompositeAstNode() {
    fun step(name: String, init: Step.() -> Unit) = initNode(Step(name), init)

    override fun toString(): String{
        return "Stage(name='$name')"
    }
}

class Step(val name : String) : AstNode()

abstract class AstNode() {

}

abstract class CompositeAstNode() : AstNode()  {
    val children = arrayListOf<AstNode>()

    fun <T : AstNode> initNode(node: T, init: T.() -> Unit): T {
        node.init()
        children.add(node)
        return node
    }
}

fun main(args: Array<String>) {

    val pl = pipeline("test-pipeline")  {
        stage("build") {
            step("first step name") {}
            betterStep("test-name") {}
        }
    }
    println(pl)


}

