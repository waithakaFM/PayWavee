# PayWavee
PayWave Implement firebase authentication and cloudstore

This app first create anonymous account for users allowing them to use the app without havong to sign in at first.
Once the user create an account, it is linked with his/her anonymous account.

It enable the user to make easily payment through M-Pesa without necessary having to other details apart from the amount and pin. The other details can be added before hand and save in the datastore to be retrive apon payment.
Then the user is shown the overview of his or her monthly spending

<div style="display:flex; flex-wrap: wrap; justify-content: center;">
  <img src="https://user-images.githubusercontent.com/92781552/225153446-b717aa54-05ea-4664-933f-51118adf5ab3.jpeg" style="width:300px; margin: 10px;" >
  <img src="https://user-images.githubusercontent.com/92781552/225153518-18b289d2-60f0-4f97-bb81-b1c4862f0f36.jpeg" style="width:300px; margin: 10px;">
  <img src="https://user-images.githubusercontent.com/92781552/230595893-cfaa3d9f-2d4c-4d53-aeee-d1962564f77f.jpeg" style="width:300px; margin: 10px;" >
  <img src="https://user-images.githubusercontent.com/92781552/230595946-6d037583-d89d-44a0-b544-acdc6b90b27b.jpeg" style="width:300px; margin: 10px;">
  <img src="https://user-images.githubusercontent.com/92781552/230596542-5d0159c3-9b05-41a0-9341-510a4210091e.jpeg" style="width:300px; margin: 10px;" >
  <img src="https://user-images.githubusercontent.com/92781552/230596978-6d234980-31b2-425d-9455-30c4066c870a.jpeg" style="width:300px; margin: 10px;" >
  <img src="https://user-images.githubusercontent.com/92781552/230596003-cfdb15cf-1fd0-46d1-947e-baea6b165d53.jpeg" style="width:300px; margin: 10px;">
</div>

# Tech Stacks and Open-Source Libraries

- [Firebase Authetication](https://firebase.google.com/docs/auth) - For user authetication and varidation.
- [Firestore](https://firebase.google.com/docs/storage) - for data storage
- [MVVM architecture](https://developer.android.com/topic/architecture) - to flow best paractices
- [Jetpack Compose](https://developer.android.com/topic/architecture) - for rendering the UI
- [Dagger hilt](https://developer.android.com/training/dependency-injection/hilt-android) -for dependency Injection
- [Mpesa stk](https://github.com/Carrieukie/android_mpesa_stk) - to intiate MPESA STK push 
- [Daraja Api](https://developer.safaricom.co.ke/) - to facilitate payment services



