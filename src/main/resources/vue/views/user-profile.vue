<template id="user-profile">
  <app-layout>
    <div v-if="noUserFound">
      <p> We're sorry, we were not able to retrieve this user.</p>
      <p> View <a :href="'/users'">all users</a>.</p>
    </div>
    <div class="card bg-light mb-3" v-if="!noUserFound">
      <div class="card-header">
        <div class="row">
          <div class="col-6"> User Profile </div>
          <div class="col" align="right">
            <button rel="tooltip" title="Update"
                    class="btn btn-info btn-simple btn-link"
                    @click="updateUser()">
              <i class="far fa-save" aria-hidden="true"></i>
            </button>
            <button rel="tooltip" title="Delete"
                    class="btn btn-info btn-simple btn-link"
                    @click="deleteUser()">
              <i class="fas fa-trash" aria-hidden="true"></i>
            </button>

          </div>
        </div>
      </div>
      <div class="card-body">
        <form>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-user-id">User ID</span>
            </div>
            <input type="number" class="form-control" v-model="user.id" name="id" readonly placeholder="Id"/>
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-user-name">Name</span>
            </div>
            <input type="text" class="form-control" v-model="user.name" name="name" placeholder="Name"/>
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="input-user-email">Email</span>
            </div>
            <input type="email" class="form-control" v-model="user.email" name="email" placeholder="Email"/>
          </div>
        </form>
      </div>

      <div class="card-footer text-left">
        <!-- activity sections-->
        <p v-if="activities.length === 0">No activities yet...</p>
        <p v-if="activities.length > 0">Activities so far:</p>
        <ul>
          <li v-for="activity in activities" :key="activity.id">
            <strong>{{ activity.description }}</strong>:
            {{ activity.duration }} minutes, {{ activity.calories }} kcal burned,
            started at {{ formatDateTime(activity.started) }}
          </li>
        </ul>
        <!-- sleep sections-->
        <p v-if="sleep.length === 0">No sleep data yet...</p>
        <ul>
          <li v-for="entry in sleep" :key="entry.id">
            Slept from {{ formatDateTime(entry.sleepStart) }} to {{ formatDateTime(entry.sleepEnd) }}
            ({{ entry.sleepDuration.toFixed(1) }} hours).
            <span v-if="entry.bedtimeReminder">(Bedtime reminder enabled)</span>
          </li>
        </ul>
        <!-- nutrition sections-->
        <p v-if="nutrition.length === 0">No nutrition data yet...</p>
        <ul>
          <li v-for="item in nutrition" :key="item.id">
            <strong>{{ item.name }}</strong> ({{ formatDateTime(item.loggedDate) }}):
            {{ item.calories }} kcal,
            {{ item.fat.toFixed(2) }}g fat,
            {{ item.carbs.toFixed(2) }}g carbs,
            {{ item.protein.toFixed(2) }}g protein,
            {{ item.waterIntake.toFixed(2) }}L water
          </li>
        </ul>
      </div>

    </div>
  </app-layout>
</template>

<script>
app.component("user-profile", {
  template: "#user-profile",
  data: () => ({
    user: null,
    noUserFound: false,
    activities: [],
    nutrition: [],
    sleep: [],
  }),
      created: function () {
        const userId = this.$javalin.pathParams["user-id"];
        const url = `/api/users/${userId}`
        axios.get(url)
            .then(res => this.user = res.data)
            .catch(error => {
              console.log("No user found for id passed in the path parameter: " + error)
              this.noUserFound = true
            })
        axios.get(url + `/activities`)
            .then(res => this.activities = res.data)
            .catch(error => {
              console.log("No activities added yet (this is ok): " + error)
            })
        axios.get(url + `/nutritions`)
            .then(res => this.nutrition = res.data)
            .catch(error => {
              console.log("No nutrition data found (this is ok): " + error);
            });

        axios.get(url + `/sleeps`)
            .then(res => this.sleep = res.data)
            .catch(error => {
              console.log("No sleep data found (this is ok): " + error);
            });
      },

  methods: {
    formatDateTime: function (value) {
      if (!value || isNaN(new Date(value))) {
        return 'Invalid date';
      }
      return new Date(value).toLocaleString();
    },


    updateUser: function () {
      const userId = this.$javalin.pathParams["user-id"];
      const url = `/api/users/${userId}`
      axios.patch(url,
          {
            name: this.user.name,
            email: this.user.email
          })
          .then(response =>
              this.user.push(response.data))
          .catch(error => {
            console.log(error)
          })
      alert("User updated!")
    },


    deleteUser: function () {
      if (confirm("Do you really want to delete?")) {
        const userId = this.$javalin.pathParams["user-id"];
        const url = `/api/users/${userId}`
        axios.delete(url)
            .then(response => {
              alert("User deleted")
              //display the /users endpoint
              window.location.href = '/users';
            })
            .catch(function (error) {
              console.log(error)
            });
      }
    }
  }
});
</script>
