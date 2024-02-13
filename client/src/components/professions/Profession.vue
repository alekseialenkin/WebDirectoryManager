<template>
  <div v-if="currentProfession" class="edit-form">
    <h4>Profession</h4>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name"
               v-model="currentProfession.name"
        />
      </div>
      <div class="form-group">
        <label for="note">Note</label>
        <input type="text" class="form-control" id="note"
               v-model="currentProfession.note"
        />
      </div>

    </form>
    <button class="badge badge-danger mr-2"
            @click="deleteProfession"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
            @click="updateProfession"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br/>
    <p>Please click on a Profession...</p>
  </div>
</template>

<script>
import ProfessionService from "@/services/ProfessionService";

export default {
  name: "professionComponent",
  data() {
    return {
      currentProfession: null,
      message: ''
    };
  },
  methods: {
    getProfession(id) {
      ProfessionService.get(id)
          .then(response => {
            this.currentProfession = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    updateProfession() {
      ProfessionService.update(this.currentProfession.id, this.currentProfession)
          .then(response => {
            console.log(response.data);
            this.message = 'The profession was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
    },
    deleteProfession() {
      ProfessionService.delete(this.currentProfession.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({name: "professions"});
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.message = '';
    this.getProfession(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>