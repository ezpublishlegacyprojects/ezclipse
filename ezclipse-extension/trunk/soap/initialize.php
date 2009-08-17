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

include 'functions.php';

$server->registerFunction( "checkAccess", array( "user" => "string",
                                           "password" => "string" ) );

$server->registerFunction( "validateTpl", array( "user" => "string",
                                           "password" => "string",
                                           "file" => "string" ) );

$server->registerFunction( "getAttributes", array( "user" => "string",
                                           "password" => "string",
                                           "nodeId" => "string",
                                           "path" => "string" ) );
?>
