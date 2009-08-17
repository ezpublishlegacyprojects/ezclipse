/*
 * WESS eZ Publish Eclipse plug-in
 * Copyright (C) 2009  Web Engineering Sahli & Stalder (http://www.wess.ch)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.wess.ezclipse.tpl;

/**
 * Class which contains a list of all function that can be used in a tpl file.
 * For each function there's a URL to the documentation.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLLanguageConstants {
    public static final String[][] OPERATOR_LIST = {
        {"append", "Returns the input array with appended elements.\n" + //$NON-NLS-1$ //$NON-NLS-2$
        		"input|append( element1 [, element2 [, ... ] ] )"},   //$NON-NLS-1$
        {"array", "template_operators/arrays/array.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"array_sum", "template_operators/arrays/array_sum.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"begins_with", "template_operators/arrays/begins_with.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"compare", "template_operators/arrays/compare.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"contains", "template_operators/arrays/contains.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ends_with", "template_operators/arrays/ends_with.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"explode", "template_operators/arrays/explode.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"extract", "template_operators/arrays/extract.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"extract_left", "template_operators/arrays/extract_left.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"extract_right", "template_operators/arrays/extract_right.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"hash", "template_operators/arrays/hash.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"implode", "template_operators/arrays/implode.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"insert", "template_operators/arrays/insert.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"merge", "template_operators/arrays/merge.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"prepend", "template_operators/arrays/prepend.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"remove", "template_operators/arrays/remove.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"repeat", "template_operators/arrays/repeat.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"reverse", "template_operators/arrays/reverse.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"unique", "template_operators/arrays/unique.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"currentdate", "template_operators/data_and_information_extraction/currentdate.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezhttp", "template_operators/data_and_information_extraction/ezhttp.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezhttp_hasvariable", "template_operators/data_and_information_extraction/ezhttp_hasvariable.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezini", "template_operators/data_and_information_extraction/ezini.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezini_hasvariable", "template_operators/data_and_information_extraction/ezini_hasvariable.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezmodule", "template_operators/data_and_information_extraction/ezmodule.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezpreference", "template_operators/data_and_information_extraction/ezpreference.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezsys", "template_operators/data_and_information_extraction/ezsys.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"fetch", "template_operators/data_and_information_extraction/fetch.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"module_params", "template_operators/data_and_information_extraction/module_params.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"datetime", "template_operators/formatting_and_internationalization/datetime.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"i18n", "template_operators/formatting_and_internationalization/i18n.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"l10n", "template_operators/formatting_and_internationalization/l10n.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"si", "template_operators/formatting_and_internationalization/si.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"image", "template_operators/images/image.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"imagefile", "template_operators/images/imagefile.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"texttoimage", "template_operators/images/texttoimage.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"and", "template_operators/logical_operations/and.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"choose", "template_operators/logical_operations/choose.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"cond", "template_operators/logical_operations/cond.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"eq", "template_operators/logical_operations/eq.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"false", "template_operators/logical_operations/false.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"first_set", "template_operators/logical_operations/first_set.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ge", "template_operators/logical_operations/ge.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"gt", "template_operators/logical_operations/gt.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"le", "template_operators/logical_operations/le.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"lt", "template_operators/logical_operations/lt.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ne", "template_operators/logical_operations/ne.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"not", "template_operators/logical_operations/not.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"null", "template_operators/logical_operations/null.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"or", "template_operators/logical_operations/or.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"true", "template_operators/logical_operations/true.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"abs", "template_operators/mathematics/abs.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ceil", "template_operators/mathematics/ceil.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"dec", "template_operators/mathematics/dec.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"div", "template_operators/mathematics/div.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"floor", "template_operators/mathematics/floor.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"inc", "template_operators/mathematics/inc.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"max", "template_operators/mathematics/max.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"min", "template_operators/mathematics/min.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"mod", "template_operators/mathematics/mod.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"mul", "template_operators/mathematics/mul.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"rand", "template_operators/mathematics/rand.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"round", "template_operators/mathematics/round.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"sub", "template_operators/mathematics/sub.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"sum", "template_operators/mathematics/sum.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"action_icon", "template_operators/miscellaneous/action_icon.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"attribute", "template_operators/miscellaneous/attribute.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"classgroup_icon", "template_operators/miscellaneous/classgroup_icon.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"class_icon", "template_operators/miscellaneous/class_icon.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"content_structure_tree", "template_operators/miscellaneous/content_structure_tree.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezpackage", "template_operators/miscellaneous/ezpackage.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"flag_icon", "template_operators/miscellaneous/flag_icon.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"gettime", "template_operators/miscellaneous/gettime.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"icon_info", "template_operators/miscellaneous/icon_info.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"makedate", "template_operators/miscellaneous/makedate.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"maketime", "template_operators/miscellaneous/maketime.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"mimetype_icon", "template_operators/miscellaneous/mimetype_icon.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"month_overview", "template_operators/miscellaneous/month_overview.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"pdf", "template_operators/miscellaneous/pdf.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"roman", "template_operators/miscellaneous/roman.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"topmenu", "template_operators/miscellaneous/topmenu.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"treemenu", "template_operators/miscellaneous/treemenu.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"append", "template_operators/strings/append.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"autolink", "template_operators/strings/autolink.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"begins_with", "template_operators/strings/begins_with.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"break", "template_operators/strings/break.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"chr", "template_operators/strings/chr.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"compare", "template_operators/strings/compare.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"concat", "template_operators/strings/concat.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"contains", "template_operators/strings/contains.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"count_chars", "template_operators/strings/count_chars.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"count_words", "template_operators/strings/count_words.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"crc32", "template_operators/strings/crc32.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"downcase", "template_operators/strings/downcase.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ends_with", "template_operators/strings/ends_with.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"explode", "template_operators/strings/explode.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"extract", "template_operators/strings/extract.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"extract left", "template_operators/strings/extract_left.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"extract_right", "template_operators/strings/extract_right.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"indent", "template_operators/strings/indent.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"insert", "template_operators/strings/insert.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"md5", "template_operators/strings/md5.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"nl2br", "template_operators/strings/nl2br.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ord", "template_operators/strings/ord.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"pad", "template_operators/strings/pad.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"prepend", "template_operators/strings/prepend.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"remove", "template_operators/strings/remove.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"repeat", "template_operators/strings/repeat.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"reverse", "template_operators/strings/reverse.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"rot13", "template_operators/strings/rot13.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"shorten", "template_operators/strings/shorten.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"simpletags", "template_operators/strings/simpletags.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"simplify", "template_operators/strings/simplify.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"trim", "template_operators/strings/trim.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"upcase", "template_operators/strings/upcase.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"upfirst", "template_operators/strings/upfirst.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"upword", "template_operators/strings/upword.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"wash", "template_operators/strings/wash.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"wordtoimage", "template_operators/strings/wordtoimage.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"wrap", "template_operators/strings/wrap.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"exturl", "template_operators/urls/exturl.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezdesign", "template_operators/urls/ezdesign.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezimage", "template_operators/urls/ezimage.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezroot", "template_operators/urls/ezroot.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ezurl", "template_operators/urls/ezurl.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"count", "template_operators/variable_and_type_handling/count.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"float", "template_operators/variable_and_type_handling/float.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"get_class", "template_operators/variable_and_type_handling/get_class.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"get_type", "template_operators/variable_and_type_handling/get_type.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"int", "template_operators/variable_and_type_handling/int.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_array", "template_operators/variable_and_type_handling/is_array.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_boolean", "template_operators/variable_and_type_handling/is_boolean.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_class", "template_operators/variable_and_type_handling/is_class.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_float", "template_operators/variable_and_type_handling/is_float.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_integer", "template_operators/variable_and_type_handling/is_integer.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_null", "template_operators/variable_and_type_handling/is_null.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_numeric", "template_operators/variable_and_type_handling/is_numeric.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_object", "template_operators/variable_and_type_handling/is_object.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_set", "template_operators/variable_and_type_handling/is_set.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_string", "template_operators/variable_and_type_handling/is_string.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"is_unset", "template_operators/variable_and_type_handling/is_unset.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"debug-accumulator", "template_functions/debugging/debug_accumulator.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"debug-timing-point", "template_functions/debugging/debug_timing_point.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"debug-trace", "template_functions/debugging/debug_trace.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"cache-block", "template_functions/miscellaneous/cache_block.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"fetch_alias", "template_functions/miscellaneous/fetch_alias.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"include", "template_functions/miscellaneous/include.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ldelim", "template_functions/miscellaneous/ldelim.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"literal", "template_functions/miscellaneous/literal.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"rdelim", "template_functions/miscellaneous/rdelim.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"run-once", "template_functions/miscellaneous/run_once.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"append-block", "template_functions/variables/append_block.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"def", "template_functions/variables/def.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"default", "template_functions/variables/default.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"let", "template_functions/variables/let.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"sequence", "template_functions/variables/sequence.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"set", "template_functions/variables/set.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"set-block", "template_functions/variables/set_block.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"undef", "template_functions/variables/undef.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"attribute_edit_gui", "template_functions/visualization/attribute_edit_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"attribute_pdf_gui", "template_functions/visualization/attribute_pdf_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"attribute_result_gui", "template_functions/visualization/attribute_result_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"attribute_view_gui", "template_functions/visualization/attribute_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"class_attribute_edit_gui", "template_functions/visualization/class_attribute_edit_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"class_attribute_view_gui", "template_functions/visualization/class_attribute_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"collaboration_icon", "template_functions/visualization/collaboration_icon.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"collaboration_participation_view", "template_functions/visualization/collaboration_participation_view.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"collaboration_simple_message_view", "template_functions/visualization/collaboration_simple_message_view.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"collaboration_view_gui", "template_functions/visualization/collaboration_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"content_pdf_gui", "template_functions/visualization/content_pdf_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"content_version_view_gui", "template_functions/visualization/content_version_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"content_view_gui", "template_functions/visualization/content_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"event_edit_gui", "template_functions/visualization/event_edit_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"node_view_gui", "template_functions/visualization/node_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"related_view_gui", "template_functions/visualization/related_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"shop_account_view_gui", "template_functions/visualization/shop_account_view_gui.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"tool_bar", "template_functions/visualization/tool_bar.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"if", "template_control_structures/conditional_control/if.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"switch", "template_control_structures/conditional_control/switch.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"do", "template_control_structures/looping/do.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"for", "template_control_structures/looping/for.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"foreach", "template_control_structures/looping/foreach.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"while", "template_control_structures/looping/while.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"section", "template_control_structures/deprecated/section.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"anchor", "template_pdf_functions/anchor.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"create_index", "template_pdf_functions/create_index.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"filled_circle", "template_pdf_functions/filled_circle.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"filled_rectangle", "template_pdf_functions/filled_rectangle.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"footer", "template_pdf_functions/footer.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"footer_block", "template_pdf_functions/footer_block.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"frame_header", "template_pdf_functions/frame_header.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"frontpage", "template_pdf_functions/frontpage.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"header", "template_pdf_functions/header.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"header_block", "template_pdf_functions/header_block.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"image", "template_pdf_functions/image.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"keyword", "template_pdf_functions/keyword.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"line", "template_pdf_functions/line.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"link", "template_pdf_functions/link.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"new_line", "template_pdf_functions/new_line.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"new_page", "template_pdf_functions/new_page.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"page_number", "template_pdf_functions/page_number.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"set_font", "template_pdf_functions/set_font.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"set_margin", "template_pdf_functions/set_margin.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"strike", "template_pdf_functions/strike.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"table", "template_pdf_functions/table.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"text", "template_pdf_functions/text.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"text_box", "template_pdf_functions/text_box.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"text_frame", "template_pdf_functions/text_frame.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"toc", "template_pdf_functions/toc.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ul", "template_pdf_functions/ul.html"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"section-else", ""}, //$NON-NLS-1$ //$NON-NLS-2$
        {"as", ""}, //$NON-NLS-1$ //$NON-NLS-2$
        {"else", ""} //$NON-NLS-1$ //$NON-NLS-2$
    };
}
