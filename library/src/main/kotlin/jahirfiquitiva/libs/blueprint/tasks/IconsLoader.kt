/*
 * Copyright (c) 2017. Jahir Fiquitiva
 *
 * Licensed under the CreativeCommons Attribution-ShareAlike
 * 4.0 International License. You may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *    http://creativecommons.org/licenses/by-sa/4.0/legalcode
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Special thanks to the project contributors and collaborators
 * 	https://github.com/jahirfiquitiva/Blueprint#special-thanks
 */

package jahirfiquitiva.libs.blueprint.tasks

import android.content.Context
import jahirfiquitiva.libs.blueprint.R
import jahirfiquitiva.libs.blueprint.extensions.blueprintFormat
import jahirfiquitiva.libs.blueprint.extensions.formatCorrectly
import jahirfiquitiva.libs.blueprint.extensions.getIconResource
import jahirfiquitiva.libs.blueprint.extensions.getStringArray
import jahirfiquitiva.libs.blueprint.extensions.sortIconsList
import jahirfiquitiva.libs.blueprint.models.Icon
import jahirfiquitiva.libs.blueprint.models.IconsCategory
import java.util.HashSet
import kotlin.collections.ArrayList

class IconsLoader(context:Context, listener:TaskListener? = null):
        BasicTaskLoader<ArrayList<IconsCategory>>(context, listener) {

    override fun loadInBackground():ArrayList<IconsCategory> {
        val categories:ArrayList<IconsCategory> = ArrayList()
        context.getStringArray(R.array.icon_filters).forEach {
            val icons:ArrayList<Icon> = ArrayList()
            val list:ArrayList<String> = ArrayList()
            list.plus(context.getStringArray(
                    context.resources.getIdentifier(it, "array",
                                                    context.packageName)))
            list.forEach {
                icons.plus(
                        Icon(it.formatCorrectly().blueprintFormat(), it.getIconResource(context)))
            }
            categories.add(IconsCategory(it, icons.sortIconsList()))
        }
        return categories
    }
}