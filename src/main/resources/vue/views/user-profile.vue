<template id="user-profile">
  <div>
    <form v-if="user">
      <label class="col-form-label">User ID: </label>
      <input class="form-control" v-model="user.id" name="id" type="number" readonly/><br>
      <label class="col-form-label">Name: </label>
      <input class="form-control" v-model="user.name" name="username" type="text"/><br>
      <label class="col-form-label">Email: </label>
      <input class="form-control" v-model="user.email" name="email" type="email"/><br>
    </form>
    <dt v-if="user">
      <br>
      <a :href="`/users/${user.id}/activities`">View User Activities</a>
    </dt>
  </div>
</template>
