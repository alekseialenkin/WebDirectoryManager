<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="department.name"
            name="name"
        />
      </div>

      <div class="form-group">
        <label for="note">Note</label>
        <input
            class="form-control"
            id="note"
            required
            v-model="department.note"
            name="note"
        />
      </div>

      <button @click="saveDepartment" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newDepartment">Add</button>
    </div>
  </div>
</template>

<script>
import DepartmentService from "@/services/DepartmentService";

export default {
  name: "add-department",
  data() {
    return {
      department: {
        id: null,
        name: "",
        note: ""
      },
      submitted: false
    };
  },
  methods: {
    saveDepartment() {
      var data = {
        name: this.department.name,
        note: this.department.note
      };

      DepartmentService.create(data)
          .then(response => {
            this.department.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
    },

    newDepartment() {
      this.submitted = false;
      this.department = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>