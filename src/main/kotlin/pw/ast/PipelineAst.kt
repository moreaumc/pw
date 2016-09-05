package pw.ast

fun pipeline(name : String, init: Pipeline.() -> Unit): Pipeline {
    val pl = Pipeline(name)
    pl.init()
    return pl
}



class Pipeline(val name: String) : CompositeAstNode() {

    fun stage(name: String, init: Stage.() -> Unit) = initNode(Stage(name), init)

    fun stageByName(name: String) : Stage {
        return children.filter { it is Stage && it.name == name}[0] as Stage
    }

    override fun toString(): String{
        return "Pipeline(name='$name')"
    }

}

class Stage(val name : String) : CompositeAstNode() {
    fun job(name: String, init: Job.() -> Unit) = initNode(Job(name), init)

    fun jobByName(name: String) : Job {
        return children.filter { it is Job && it.name == name}[0] as Job
    }

    override fun toString(): String{
        return "Stage(name='$name')"
    }
}

class Job(val name : String) : CompositeAstNode() {

    fun maven(target: String, init: Maven.() -> Unit) = initNode(Maven(target), init)

    override fun toString(): String{
        return "Job(name='$name')"
    }

}

abstract class Step() : AstNode()

class Maven(val target : String?) : Step()

abstract class AstNode() {}

abstract class CompositeAstNode() : AstNode()  {
    val children = arrayListOf<AstNode>()

    fun <T : AstNode> initNode(node: T, init: T.() -> Unit): T {
        node.init()
        children.add(node)
        return node
    }
}
