<?php

use App\Http\Controllers\DestinationController;
use App\Http\Controllers\OrderController;
use App\Http\Controllers\OrderRequestController;
use App\Http\Controllers\PackageTypeController;
use App\Http\Controllers\UserController;
use App\Models\Destination;
use App\Models\OrderRequest;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});


Route::post("user/login",[UserController::class, 'login'])->name('login');
Route::apiResource("user", UserController::class);
Route::apiResource("orders",OrderController::class);
Route::apiResource("destination",DestinationController::class);
Route::apiResource("order-requests",OrderRequestController::class);
Route::apiResource("package-type",PackageTypeController::class);
