/* Notes: Maven can be invoked via command line or via the Jenkins maven plugin.
Need to add a selector to choose implementation.
*/
package pw.step

import pw.ast.*

fun Job.maven(targetPath: String, init: MavenStep.() -> Unit) = this.initNode(MavenStep(targetPath), init)

class MavenStep(val targetPath : String) : Step()
