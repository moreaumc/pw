package pw.experimental

import pw.ast.*

fun Stage.betterStep(name: String, init: BetterStep.() -> Unit) = this.initNode(BetterStep(name), init)

class BetterStep(val name : String) : AstNode()
