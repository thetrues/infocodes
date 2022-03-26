<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class UserController extends Controller
{
      /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            "username"=>"required",
            "phone"=>"required|unique:users,phone",
            "email"=>"required|unique:users,email",
            "password"=>"required",
            "address"=>"required",
            "role"=>"required"
        ]);

        $validated["password"] = Hash::make($validated["password"]);

        $user = User::create($validated);
        $token =  $user->createToken($request->username)->plainTextToken;

        return response()->json( ["user"=>$user, "token"=>$token]);
    }

    public function login(Request $request)
    {
       $validated = $request->validate([
           'username'=>"required",
           'password'=>"required",
       ]);


       $user = User::where('username', $validated['username'])->first();
       if($user == null){
            return response()->json(["message"=>"Invalid credentials 2"]);
       }

       if( !Hash::check($validated["password"],$user->password)){
        return response()->json(["message"=>"Invalid credentials 3"]);
       }


        $token =  $user->createToken($request->username)->plainTextToken;

        return response()->json( ["user"=>$user, "token"=>$token]);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\User  $User
     * @return \Illuminate\Http\Response
     */
    public function show(User $User)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\User  $User
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, User $User)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\User  $User
     * @return \Illuminate\Http\Response
     */
    public function destroy(User $User)
    {
        //
    }
}
