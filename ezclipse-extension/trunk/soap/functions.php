<?php
/*
 * WESS eZclipse extension
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

require_once( 'kernel/common/template.php' );

/**
 * Validate a template file if the user is an administrator.
 *
 * @param $user the user.
 * @param $password the password.
 * @param $file the path to the file to validate.
 *
 * @return boolean True if the template file has no errors. Else an array which
 * contains a list of errors.
 */
function validateTpl($user, $password, $file)
{
    $access = checkAccess( $user, $password );
    if ( $access )
    {
        return $access;
    }
    
    $tpl = templateInit();
    $valid = $tpl->validateTemplateFile( $file );
    if( $valid )
        return true;

    $errors = array();
    foreach($tpl->errorLog() as $error)
    {        
        if ( isset( $error['placement'][0][0] ) and isset( $error['placement'][0][1] )
             and isset( $error['placement'][1][0] ) and isset( $error['placement'][1][1] ) )
        {
            $errors[] = array(
                $error['text'], // Error text
                $error['placement'][0][0], // Start line
                $error['placement'][0][1], // Start char
                $error['placement'][1][0], // End line
                $error['placement'][1][1] // End char
            );
        }
        elseif( isset( $error['placement']['start']['position'] ) 
                and isset( $error['placement']['stop']['position'] ) )
        {
            $errors[] = array(
                $error['text'], // Error text
                $error['placement']['start']['position'],
                $error['placement']['stop']['position']
            );
        }
        else
        {
            $errors[] = array($error['text']);
        }
    }
    
    /*foreach($tpl->warningLog() as $warning) 
    {
        $errors[] = $warning;
    }*/

    return $errors;
}

/**
 * Return the attributes for the given path.
 *
 * @param $user an admin user
 * @param $password the password
 * @param $nodeId the nodeId to fetch
 * @param $path the path without the $node. For example data_map.an_attribute.
 *
 * @return Array list of children
 */
function getAttributes($user, $password, $nodeId, $path = '')
{
    $access = checkAccess( $user, $password );
    if ( $access )
    {
        return $access;
    }
    
    $node = eZContentObjectTreeNode::fetch( $nodeId );

    if($path == '')
    {
        return displayVariable($node, 1, 0);
    }
    else
    {
        $methods = explode( '.', $path );

        $finalNode = fetchFinalNode( $node, $methods );
        if($finalNode != null)
            return displayVariable( $finalNode, 1, 0 );
        else
            return false;    
    } 
}

/**
 * Return the end node of the path
 *
 * @param $node the root node.
 * @param $node the list of methods to execute.
 * @param $i iterator for the recursive loop.
 * 
 * @return eZContentObjectTreeNode end node.
 */
function fetchFinalNode( $node, $methods, $i = 0 )
{
    if ( isset( $methods[$i] ) )
    {    
        if ( is_object( $node ) )
            $finalNode = $node->attribute( $methods[$i] );               
        elseif (is_array( $node ) )
            $finalNode = $node[$methods[$i]];
        else
            return null;
            
        return fetchFinalNode( $finalNode, $methods, $i + 1 );
    }
    
    return $node;
}

/**
* Helper function for recursive display of attributes.
* This function was copied and modified from the file eztemplateattributeoperator.php.
* 
* @param $value is the current variable, 
* @param $as_html is true if display as html,
* @param $max is the maximum number of levels, 
* @param $cur_level the current level
* @param $txt is the output text which the function adds to.
*/
function displayVariable( &$value, $max, $cur_level )
{
    $result = array();
    $tabIndex = 0;
    if ( $max !== false and $cur_level >= $max )
        return '';
    if ( is_array( $value ) )
    {
        foreach( $value as $key => $item )
        {
            $type = gettype( $item );
            if ( is_object( $item ) )
                $type .= "[" . get_class( $item ) . "]";

            if ( is_bool( $item ) )
                $itemValue = $item ? "true" : "false";
            else if ( is_array( $item ) )
                $itemValue = 'Array(' . count( $item ) . ')';
            else if ( is_string( $item ) )
                $itemValue = "'" . $item . "'";
            else if ( is_object( $item ) )
                $itemValue = method_exists( $item, '__toString' ) ? (string)$item : 'Object';
            else
                $itemValue = $item;

            $result[$tabIndex][0] = $key;
            $result[$tabIndex][1] = $type;
            $result[$tabIndex][2] = $itemValue;
            $tabIndex++;
        }
    }
    else if ( is_object( $value ) )
    {
        if ( !method_exists( $value, "attributes" ) or
             !method_exists( $value, "attribute" ) )
            return;
        $attrs = $value->attributes();
        foreach ( $attrs as $key )
        {
            $item = $value->attribute( $key );
            $type = gettype( $item );
            if ( is_object( $item ) )
                $type .= "[" . get_class( $item ) . "]";

            if ( is_bool( $item ) )
                $itemValue = $item ? "true" : "false";
            else if ( is_array( $item ) )
                $itemValue = 'Array(' . count( $item ) . ')';
            else if ( is_numeric( $item ) )
                $itemValue = $item;
            else if ( is_string( $item ) )
                $itemValue = "'" . $item . "'";
            else if ( is_object( $item ) )
                $itemValue = method_exists( $item, '__toString' ) ? (string)$item : 'Object';
            else
                $itemValue = $item;
            
            $result[$tabIndex][0] = $key;
            $result[$tabIndex][1] = $type;
            $result[$tabIndex][2] = $itemValue;
            $tabIndex++;
        }
    }
    
    return $result;
}

/**
 * Check if the user has admin access.
 *
 * @param $user the user.
 * @param $password the password.
 *
 * @return False if the user has access, eZSOAPFault if he hasn't access.
 */
function checkAccess( $user, $password )
{
    $return = false;
    
    $currentUser = eZUser::loginUser( $user, $password );
    if ( $currentUser )
    {
        $accessArray = eZRole::accessArrayByUserID( array_merge( $currentUser->groups(), array( $currentUser->attribute( 'contentobject_id' ) ) ) );

        if ( key( $accessArray ) == "*" and key ( $accessArray['*'] ) == "*"  )
        {
        }
        else
        {
            $return =  new eZSOAPFault( "1", "Access denied" );
        }
    }
    else
    {
        $return =  new eZSOAPFault( "1", "Access denied " . $user . ' ' . $password );
    }
    return $return;
}
