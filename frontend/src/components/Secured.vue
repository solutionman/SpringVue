<template>
  <v-container>
    <v-layout row>
      <v-flex
        xs12
        class="text-center"
        mt-5
      >
        <h1>Secured area</h1>
        <v-btn
          dark
          color="blue darken-1"
          class="ma-2"
          @click="getSecuredUserInformation"
        >
          Call secured user service
        </v-btn>
        <v-btn
          dark
          color="blue darken-1"
          class="ma-2"
          @click="getSecuredAdminInformation"
        >
          Call secured admin service
        </v-btn>
        <v-btn
          dark
          color="blue darken-1"
          class="ma-2"
          @click="postData"
        >
          Make secured POST request
        </v-btn>
      </v-flex>

      <v-flex
        xs8
        offset-xs2
        class="text-left"
        mt-5
      >
        <h2>Request URL: {{ responseObj.url }}</h2>
        <h2>Request method: {{ responseObj.method }}</h2>
        <h2>Status code: {{ responseObj.statusCode }}</h2>
        <h2>Response: {{ responseObj.msg }}</h2>
        <h2>X-XSRF-TOKEN: {{ responseObj.xsrfToken }}</h2>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      responseObj: {
        url: '',
        statusCode: '',
        method: '',
        msg: '',
        xsrfToken: '',
      },
    };
  },
  created() {
  },
  methods: {
    getSecuredUserInformation() {
      this.responseObj = {};
      this.$axios
        .get('http://localhost:8080/backend/secured/welcome')
        .then((response) => {
          console.log('Get response: ', response.data);
          this.responseObj = this.parseResponse(response);
        })
        .catch((error) => {
          this.alert = true;
          this.responseObj = this.parseResponse(error);
        });
    },
    getSecuredAdminInformation() {
      this.responseObj = {};
      this.$axios
        .get('http://localhost:8080/backend/onlyforadmin/welcome')
        .then((response) => {
          console.log('Get response: ', response.data);
          this.responseObj = this.parseResponse(response);
        })
        .catch((error) => {
          this.alert = true;
          this.responseObj = this.parseResponse(error);
        });
    },
    postData() {
      this.responseObj = {};
      this.$axios({
        method: 'post',
        url: 'http://localhost:8080/backend/secured/postdata',
        headers: {}
      })
        // .post('http://localhost:8080/backend/secured/postdata')
        .then((response) => {
          console.log('Get response: ', response.data);
          this.responseObj = this.parseResponse(response);
        })
        .catch((error) => {
          this.alert = true;
          this.responseObj = this.parseResponse(error);
        });
    },
    parseResponse(response) {
      console.log(response);
      const respObj = {};
      respObj.url = response.config.url;
      respObj.statusCode = response.status;
      respObj.method = response.config.method;
      respObj.msg = response.data.message ? response.data.message : response.data;
      respObj.xsrfToken = response.config.headers['X-XSRF-TOKEN'];
      return respObj;
    },
  },
};
</script>
